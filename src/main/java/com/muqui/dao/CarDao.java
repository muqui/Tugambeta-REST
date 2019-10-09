/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqui.dao;

import com.muqui.model.Car;

import com.muqui.model.Users;

/**
 *
 * @author mq12
 */
public interface CarDao {
    public void saveCar(Car car);

    public Users findByUserName(String username);

    public Car getCar(Integer id);
}
