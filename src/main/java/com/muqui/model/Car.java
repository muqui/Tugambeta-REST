/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqui.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author mq12
 */
@Entity
@Table(name = "car",
        catalog = "usuario"
)
public class Car implements java.io.Serializable {

    private int id;
    private String make;
    private String model;
    private Integer year;

    public Car() {
    }

    public Car(String make, String model, Integer year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }
    
    

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
        @Column(name="make", nullable=false)
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    
        @Column(name="model", nullable=false)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    
     @Column(name="year")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

}
