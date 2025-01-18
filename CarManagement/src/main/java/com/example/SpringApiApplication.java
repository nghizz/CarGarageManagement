package com.example;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example"})
public class SpringApiApplication {
    public static void main(String[] args) throws IOException, URISyntaxException {
        SpringApplication.run(SpringApiApplication.class, args);
    }

}