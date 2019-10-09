/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqui.controller;

import com.muqui.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.muqui.service.CarService;

/**
 *
 * @author mq12
 */
@RestController
public class RestCarController {
    
     @Autowired
    CarService carService;
    
    
     /*---Get a car by id---*/
   @GetMapping("/car/{id}")
   public ResponseEntity<Car> get(@PathVariable("id") Integer id) {  
       Car car = carService.getCar(id);
      return ResponseEntity.ok().body(car);
   }
   
    /*---Add new car---*/
   @PostMapping("/car")
   public ResponseEntity<?> save(@RequestBody Car car) {
     
     carService.saveCar(car);
      return ResponseEntity.ok().body("New Car has been saved with Model:"+ car.getModel());
   }
}
