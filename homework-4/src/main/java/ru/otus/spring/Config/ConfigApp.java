package ru.otus.spring.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Locale;

@Configuration
public class ConfigApp {

    @Value("${app.csv}")
    private String questionsAndAnswers;

    @Bean
    public Resource getLocaleResource(Locale locale) {
        return new ClassPathResource(questionsAndAnswers + "_" + locale.getLanguage() + ".csv");
    }

    @Bean
    public Locale getLocale() {
        return Locale.getDefault();
    }
}
    