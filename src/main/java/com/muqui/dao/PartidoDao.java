/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqui.dao;

import com.muqui.model.Jugador;
import com.muqui.model.Pagina;
import com.muqui.model.Partidos;
import java.util.List;

/**
 *
 * @author mq12
 */
public interface PartidoDao {

    public List<Partidos> getPartidos();

    public List<Partidos> getPartidos(String liga);
    
    public int jugar(Jugador jugador, String userId);

    public Pagina getPagina(String ligamx);
    
    List<List<String>> getParticipantesSinLogin(Integer actual, String userId);
}
