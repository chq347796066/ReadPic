package com.pose.readpicredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.pose.readpicredis"})
public class ReadpicredisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadpicredisApplication.class, args);
    }

}

