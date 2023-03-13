package ru.otus.spring.service.l10n.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.l10n.LocaleProvider;

import java.util.Locale;

@Service
public class LocaleProviderImpl implements LocaleProvider {
    private final Locale locale;

    public LocaleProviderImpl(@Value("${app.locale:ru}") Locale locale) {
        this.locale = locale;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }
}
