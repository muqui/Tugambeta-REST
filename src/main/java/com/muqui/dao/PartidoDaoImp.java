/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqui.dao;

import com.muqui.helper.Codigo;
import com.muqui.helper.FechaLimite;
import com.muqui.model.Jugador;
import com.muqui.model.Jugadorresultados;
import com.muqui.model.Pagina;
import com.muqui.model.Partidos;
import com.muqui.model.Quiniela;
import com.muqui.model.Users;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mq12
 */
@Repository("partidoDao")
public class PartidoDaoImp implements PartidoDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public List<Partidos> getPartidos() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Partidos ");

        List<Partidos> list = query.list();
        if (list.size() > 0) {
            System.out.println("listawqwq " + list);
            return list;
        } else {
            return null;
        }
    }

    @Override
    public List<Partidos> getPartidos(String liga) {
        Pagina pagina = getPagina(liga);
        int actual = -10;
        if (pagina != null) {
            actual = pagina.getActual();
        }

        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Partidos as p  WHERE p.quiniela.idquiniela = :id");
        query.setParameter("id", actual);
        List<Partidos> list = query.list();
        if (list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }

    public Pagina getPagina(String nombre) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Pagina as p WHERE p.nombre= :nombre ");
        query.setParameter("nombre", nombre);
        List<?> list = query.list();

        if (list.size() > 0) {
            Pagina u = (Pagina) list.get(0);
            return u;
        } else {
            return null;
        }
    }

    @Override
    public int jugar(Jugador jugador, String userId) {
        Session session = this.sessionFactory.getCurrentSession();
        Codigo codigo = new Codigo();
        int vigente = 0;
        List<Jugadorresultados> misResultados = new LinkedList<Jugadorresultados>();
        for (Partidos p : jugador.getQuiniela().getPartidoses()) {
            Jugadorresultados jugadorresultados = new Jugadorresultados(null, p, null, p.getResultado());

            misResultados.add(jugadorresultados);
        }
        Pagina pagina = getPagina(userId);
        Quiniela quiniela = getQuiniela(pagina.getActual());
        vigente = new FechaLimite().Limite(quiniela.getFechaLimite());
        if (vigente == -1) {//esta vigente
            jugador.setAciertos(0);
            jugador.setCodigo(codigo.getCadenaAlfanumAleatoria());
            jugador.setQuiniela(quiniela);

            for (Partidos p : jugador.getQuiniela().getPartidoses()) {
//                System.out.println("Resultado xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" + p.getResultado());
//                System.out.println("id partido" + p.getIdpartidos());
            }

//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            String username = authentication.getName();
//            jugador.setUsers(new Users(username, true)); //corregir como guardar el user
            jugador.setUsers(findByUserName(userId));
            session.persist(jugador);

            Jugador j = getJugador(jugador.getCodigo(), jugador.getJugador());
            for (Jugadorresultados r : misResultados) {
                r.setJugador(j);
                r.setQuiniela(quiniela);

                session.persist(r);
            }

        } else {
            System.out.println("NO ESTA VIGENTE ...................................................................................");
        }

        return vigente;
    }

    public Quiniela getQuiniela(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Quiniela as q WHERE q.idquiniela   =" + id);
        List<?> list = query.list();
        Quiniela q = (Quiniela) list.get(0);
        return q;
    }

    public Users findByUserName(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Users where username= :username ");
        query.setParameter("username", username);
        List<?> list = query.list();
        Users u = (Users) list.get(0);

        System.out.println(u);

        return u;
    }

    public Jugador getJugador(String codigo, String nombre) {
        System.out.println("Jugador " + nombre + "codigo " + codigo);
        Session session = this.sessionFactory.getCurrentSession();

        Query query = session.createQuery("FROM Jugador as j WHERE j.jugador ='" + nombre + "'  and j.codigo  ='" + codigo + "'");
        System.out.println();
        List<?> list = query.list();
        Jugador jugador = (Jugador) list.get(0);
        return jugador;
    }

    public List<List<String>> getParticipantesSinLogin(Integer actual, String userId) {
        List<List<String>> totalParticipantes = new ArrayList<List<String>>();
        List<String> participante = null;
        Quiniela q = getQuiniela(actual);

        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Jugador as j WHERE j.quiniela.idquiniela = :id and j.users.username = :username order by j.aciertos desc ");
        query.setParameter("id", q.getIdquiniela());

        query.setParameter("username", userId);
        List<Jugador> jugadores = query.list();
        participante = new ArrayList<String>();
        participante.add("Jugador");
        participante.add("Codigo");

        for (Partidos p : q.getPartidoses()) {
            participante.add("" + p.getLocal());

        }
        participante.add("Aciertos");
        totalParticipantes.add(participante);

        for (Jugador j : jugadores) {
            participante = new ArrayList<String>();
            participante.add("" + j.getJugador());
            participante.add("" + j.getCodigo());
            for (Partidos p : q.getPartidoses()) {
                System.out.println("id partido " + p.getIdpartidos());
                for (Jugadorresultados r : j.getJugadorresultadoses()) {
                    int idp = p.getIdpartidos();
                    int idj = r.getPartidos().getIdpartidos();
                    if (idp == idj) {
                        System.out.println("Local: " + p.getLocal() + " visita: " + p.getVisitante() + " id_partido: " + p.getIdpartidos() + " mi_resultado: " + r.getResultado() + "mi_resultado_id" + r.getPartidos().getIdpartidos());
                        participante.add("" + r.getResultado());
                    }
                }

            }
            participante.add("" + j.getAciertos());
            totalParticipantes.add(participante);
        }

        return totalParticipantes;
    }
}
