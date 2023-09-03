package ru.otus.spring;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
@EnableMongock
public class Homework14Application {
    @Autowired
    private static ApplicationContext applicationContext;
    public static void main(String[] args) {
        System.out.println("http://localhost:8080/");
        SpringApplication.run(Homework14Application.class, args);


// Получение бина по имени
        var springShellBean = applicationContext.getBean("springShellBeanName");
        System.out.println(springShellBean);
    }
}
