/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqui.service;

import com.muqui.model.Car;


/**
 *
 * @author mq12
 */
public interface CarService {
     public void saveCar(Car car);
     public Car getCar(Integer id);
    
}
