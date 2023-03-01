package ru.otus.spring.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class ResourceConfig {

    @Value("${app.csv}")
    private String questionsAndAnswers;

    @Value("${app.locale}")
    private String locale;

    @Bean
    public Resource getLocaleResourceCsv() {
        return new ClassPathResource(questionsAndAnswers + "_" + locale + ".csv");
    }
}
