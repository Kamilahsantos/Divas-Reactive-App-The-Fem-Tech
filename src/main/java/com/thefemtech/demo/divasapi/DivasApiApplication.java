package com.thefemtech.demo.divasapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class DivasApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DivasApiApplication.class, args);
        System.out.println("------------------------Starting  Divas App------------");
    }

}
