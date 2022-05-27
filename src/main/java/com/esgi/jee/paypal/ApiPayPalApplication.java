package com.esgi.jee.paypal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class ApiPayPalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiPayPalApplication.class, args);
    }

}
