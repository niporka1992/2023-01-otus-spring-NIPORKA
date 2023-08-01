package ru.otus.spring;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.spring.controllers.rest.BookRestController;

@SpringBootApplication
@EnableMongock
public class Homework11Application {

    public static void main(String[] args) {

        System.out.println("http://localhost:8080/");
        SpringApplication.run(Homework11Application.class, args);

    }
}
