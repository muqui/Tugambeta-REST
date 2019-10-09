/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqui.service;

import com.muqui.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.muqui.dao.CarDao;

/**
 *
 * @author mq12
 */
@Service("carService")
@Transactional
public class CarServiceImp implements CarService{
        @Autowired
    private CarDao dao;

    @Override
    public void saveCar(Car car) {
       dao.saveCar(car);
    }

    @Override
    public Car getCar(Integer id) {
       return dao.getCar(id);
    }
    
    
}
