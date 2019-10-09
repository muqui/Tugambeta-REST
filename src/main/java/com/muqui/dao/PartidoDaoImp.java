/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqui.dao;

import com.muqui.model.Partidos;
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
            System.out.println("listawqwq " +  list);
            return list;
        } else {
            return null;
        }
    }

}
