/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqui.controller;

import com.muqui.model.Partidos;
import com.muqui.service.PartidoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mq12
 */
@RestController
public class RestPartidosController {
     @Autowired
     PartidoService partidoService;
      /*---obtiene todos los partidos---*/
   @GetMapping("/partidos")
   public ResponseEntity<List<Partidos>> list() {
       List<Partidos> partidos = partidoService.getPartidos();
      return ResponseEntity.ok().body(partidos);
   }
    
}
