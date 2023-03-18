package ru.otus.spring.service.l10n.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.l10n.LocaleProvider;
import ru.otus.spring.service.l10n.LocalizationMessageService;

@Service
@RequiredArgsConstructor
public class LocalizationMessageServiceImpl implements LocalizationMessageService {

    private final LocaleProvider localeProvider;
    private final MessageSource messageSource;

    @Override
    public String localizeTheMessage(String message) {
        return messageSource.getMessage(message, null, localeProvider.getLocale());
    }
}
