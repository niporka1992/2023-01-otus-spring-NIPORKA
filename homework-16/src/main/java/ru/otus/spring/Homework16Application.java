package ru.otus.spring;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableMongock
public class Homework16Application {

    public static void main(String[] args) {
        System.out.println("http://localhost:8080/");
        SpringApplication.run(Homework16Application.class, args);

    }
}
