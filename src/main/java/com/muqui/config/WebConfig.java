/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqui.config;

/**
 *
 * @author mq12
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

@Configuration
@EnableWebMvc
@Import({SpringSecurityConfig.class})
/*Lugar donde se buscan los controladores*/
@ComponentScan(basePackages = {"com.muqui"})
public class WebConfig extends WebMvcConfigurerAdapter {

    /*cambios para servidor REST
      resuelve el problema:
    Could not write JSON: could not initialize proxy - no Session; nested 
    exception is com.fasterxml.jackson.databind.JsonMappingException: 
    could not initialize proxy - no Session
     */
    public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

        ObjectMapper mapper = new ObjectMapper();
        //Registering Hibernate4Module to support lazy objects
        mapper.registerModule(new Hibernate4Module());

        messageConverter.setObjectMapper(mapper);
        return messageConverter;

    }

    /*cambios para servidor REST
      resuelve el problema:
    Could not write JSON: could not initialize proxy - no Session; nested 
    exception is com.fasterxml.jackson.databind.JsonMappingException: 
    could not initialize proxy - no Session
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //Here we add our custom-configured HttpMessageConverter
        converters.add(jacksonMessageConverter());
        super.configureMessageConverters(converters);
    }

}
