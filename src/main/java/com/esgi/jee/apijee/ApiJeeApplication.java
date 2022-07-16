package com.esgi.jee.apijee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
public class ApiJeeApplication  {

    public static void main(String[] args) {
        SpringApplication.run(ApiJeeApplication.class, args);
    }

}