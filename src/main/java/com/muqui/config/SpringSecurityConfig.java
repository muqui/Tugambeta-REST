/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqui.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        
        
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
       //       auth.userDetailsService(userDetailsService);

 

    }

    // Secure the endpoins with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                /* url de jugador*/
                 .antMatchers("/grupo/**").access("hasRole('JUGADOR') ")
                .antMatchers(HttpMethod.GET, "/grupo/**").hasRole("JUGADOR")
                .antMatchers(HttpMethod.POST, "/grupo").hasRole("JUGADOR")
                .antMatchers(HttpMethod.PUT, "/grupo/**").hasRole("JUGADOR")
                .antMatchers(HttpMethod.PATCH, "/grupo/**").hasRole("JUGADOR")
                .antMatchers(HttpMethod.DELETE, "/grupo/**").hasRole("JUGADOR")

                .and()
                .csrf().disable()
                .formLogin().disable();
    }

      @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

}
