/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqui.controller;

import com.muqui.model.Car;
import com.muqui.model.Jugador;
import com.muqui.model.Jugadorresultados;
import com.muqui.model.Pagina;
import com.muqui.model.Partidos;
import com.muqui.service.PartidoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        List<Partidos> partidos = partidoService.getPartidos("ligamx");
        return ResponseEntity.ok().body(partidos);
    }
    //

    /**
     * add player
     * @param jugador
     * @return
     * La clase Jugador debe contener los siguientes parametros para su correcto funcionamiento
     * Ejemplo:
     * Datos json
     *  {
     *   "jugador": "alberto",
     *  "quiniela": {"idquiniela": 1 , "nombre":"liga mx 13", "partidoses": [{"resultado":"G","local":"Veracruz", "visitante":"Morelia", "idpartidos": 1}]},
     *   "codigo": "codigo",
     *   "aciertos": 0
     *   
     *   }
     */
    @PostMapping("/jugar")
    public ResponseEntity<?> save(@RequestBody Jugador jugador) {
        String vigente = String.valueOf(partidoService.jugar(jugador, "ligamx"));           
        return ResponseEntity.ok().body("New player has been saved with Model:" + jugador.getQuiniela());
    }

     /*---obtiene todos los partidos---*/
    @GetMapping("/participantes")
    public ResponseEntity< List<List<String>> > listParticipantes() {
         Pagina p = partidoService.getPagina("ligamx");
         
           List<List<String>> participantes = partidoService.getParticipantesSinLogin(p.getActual(), "ligamx"); ;
        return ResponseEntity.ok().body(participantes);
    }
}