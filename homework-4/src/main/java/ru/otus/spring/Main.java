package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.service.QuestionService;

@SpringBootApplication
@PropertySource("/application.yml")
public class Main {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Main.class, args);
        QuestionService service = context.getBean(QuestionService.class);

        service.run();

    }
}
