package ru.otus.spring.service.l10n;

public interface LocalizationIOService {
    void outputMessage(String name);

    String getMessage(String name);

    String readString();
}

