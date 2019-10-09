/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqui.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author mq12
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    
    
    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    // Create 2 users for demo
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        
     //   auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
              auth.userDetailsService(userDetailsService);

//        auth.inMemoryAuthentication()
//                .withUser("user").password("123456").roles("USER")
//                .and()
//                .withUser("admin").password("123456").roles("USER", "ADMIN");

    }

    // Secure the endpoins with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                 .antMatchers("/car/**").access("hasRole('ADMIN') ")
                .antMatchers(HttpMethod.GET, "/car/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/car").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/car/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/car/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/car/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

   

}
