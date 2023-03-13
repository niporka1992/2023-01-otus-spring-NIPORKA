package ru.otus.spring.service.l10n.impl;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.IO.IOService;
import ru.otus.spring.service.l10n.LocaleProvider;
import ru.otus.spring.service.l10n.LocalizationIOService;

@Service
public class LocalizationIOServiceImpl implements LocalizationIOService {

    private final MessageSource messageSource;
    private final IOService ioService;
    private final LocaleProvider localeProvider;

    public LocalizationIOServiceImpl(MessageSource messageSource, IOService ioService, LocaleProvider localeProvider) {
        this.messageSource = messageSource;
        this.ioService = ioService;
        this.localeProvider = localeProvider;

    }

    @Override
    public void outputMessage(String name) {
        String s = messageSource.getMessage(name, null, localeProvider.getLocale());
        ioService.outputString(s);
    }

    @Override
    public String getMessage(String name) {
        return messageSource.getMessage(name, null, localeProvider.getLocale());
    }
}
