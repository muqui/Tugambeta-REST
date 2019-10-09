/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqui.service;

import com.muqui.dao.PartidoDao;
import com.muqui.model.Partidos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mq12
 */
@Service("partidoService")
@Transactional
public class PartidoServiceImp implements PartidoService {

    @Autowired
    private PartidoDao dao;

    @Override
    public List<Partidos> getPartidos() {
       return dao.getPartidos();
    }

}
