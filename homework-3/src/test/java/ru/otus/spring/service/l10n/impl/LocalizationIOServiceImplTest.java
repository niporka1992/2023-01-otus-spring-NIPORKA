package ru.otus.spring.service.l10n.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LocalizationIOServiceImplTest {

    @Autowired
    MessageSource messageSource;

    @DisplayName("Выбор ru локали в message.properties")
    @Test
    void getMessageRU() {
        String s = messageSource.getMessage("string.from", null, Locale.forLanguageTag("ru"));
        assertEquals("из", s);
    }

    @DisplayName("Выбор en локали в message.properties")
    @Test
    void getMessageEN() {
        String s = messageSource.getMessage("string.from", null, Locale.forLanguageTag("en"));
        assertEquals("from", s);
    }
}