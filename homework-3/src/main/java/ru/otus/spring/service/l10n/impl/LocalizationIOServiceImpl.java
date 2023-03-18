package ru.otus.spring.service.l10n.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.IO.IOService;
import ru.otus.spring.service.l10n.LocalizationIOService;
import ru.otus.spring.service.l10n.LocalizationMessageService;

@Service
@RequiredArgsConstructor
public class LocalizationIOServiceImpl implements LocalizationIOService {
    private final IOService ioService;
    private final LocalizationMessageService localizationMessageService;

    @Override
    public void outputMessage(String message) {
        String ss = localizationMessageService.localizeTheMessage(message);
        ioService.outputString(ss);
    }

    public String getMessage(String message) {
        return localizationMessageService.localizeTheMessage(message);
    }

    @Override
    public String readString() {
        return ioService.readString();
    }
}
