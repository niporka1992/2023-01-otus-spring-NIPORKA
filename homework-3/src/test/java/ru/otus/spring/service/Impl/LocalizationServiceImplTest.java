package ru.otus.spring.service.Impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LocalizationServiceImplTest {

    @Autowired
    MessageSource messageSource;

    @DisplayName("Выбор EN локали в message.properties")
    @Test
    void getMessageEN() {
        String s = messageSource.getMessage("string.from", null, Locale.ENGLISH);
        assertEquals("from", s);
    }

    @DisplayName("Выбор RU локали в message.properties")
    @Test
    void getMessageRU() {
        String s = messageSource.getMessage("string.from", null, Locale.forLanguageTag("RU"));
        assertEquals("из", s);
    }
}