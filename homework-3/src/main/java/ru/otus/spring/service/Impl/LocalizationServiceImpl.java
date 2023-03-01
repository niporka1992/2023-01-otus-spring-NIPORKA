package ru.otus.spring.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.LocalizationService;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class LocalizationServiceImpl implements LocalizationService {

    private final MessageSource messageSource;

    @Override
    public String getMessage(String name, String locale) {
        return messageSource.getMessage(name, null, Locale.forLanguageTag(locale));
    }


}
