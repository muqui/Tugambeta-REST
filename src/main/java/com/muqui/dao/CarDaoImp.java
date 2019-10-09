/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqui.dao;

import com.muqui.model.Car;

import com.muqui.model.Users;
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
@Repository("userDao")
public class CarDaoImp implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public void saveCar(Car car) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(car);

        session.getTransaction().commit();
       
    }

    @Override
        public Users findByUserName(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Users where username= :username ");
        query.setParameter("username", username);
        List<?> list = query.list();
        Users u = (Users) list.get(0);

        System.out.println(u);

        return u;
    }

    @Override
    public Car getCar(Integer id) {
         Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Car where id= :id ");
        query.setParameter("id", id);
        List<?> list = query.list();
         Car car = (Car) list.get(0);
         
         return car;
         
    }

}
