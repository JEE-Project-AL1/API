package com.esgi.jee.apijee;

import com.esgi.jee.apijee.user.application.UserService;
import com.esgi.jee.apijee.user.domain.Role;
import com.esgi.jee.apijee.user.domain.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiJeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiJeeApplication.class, args);
    }



    }