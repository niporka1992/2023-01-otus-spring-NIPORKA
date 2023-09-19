package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.domain.Caterpillar;

@SpringBootApplication
public class Homework15Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Homework15Application.class, args);
        Incubator incubator = context.getBean(Incubator.class);

        new Thread(() -> {
            while (true) {
                incubator.process(new Caterpillar());
            }
        }).start();
    }
}
