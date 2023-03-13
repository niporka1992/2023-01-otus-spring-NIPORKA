package ru.otus.spring.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.spring.service.QuestionResourceNameProvider;
import ru.otus.spring.service.l10n.LocaleProvider;

@Component
public class QuestionResourceNameProviderImpl implements QuestionResourceNameProvider {
    private static final String EXT = ".csv";
    private final String defaultResourceName;
    private final LocaleProvider localeProvider;


    public QuestionResourceNameProviderImpl(@Value("${app.csv}") String defaultResourceName,
                                            LocaleProvider localeProvider) {
        this.defaultResourceName = defaultResourceName;
        this.localeProvider = localeProvider;
    }

    @Override
    public String getResourceName() {

        var separator = "_";
        var language = localeProvider.getLocale().getLanguage();
        return defaultResourceName + separator + language + EXT;
    }
}
