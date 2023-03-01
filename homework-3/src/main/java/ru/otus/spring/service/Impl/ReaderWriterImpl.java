package ru.otus.spring.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.LocalizationService;
import ru.otus.spring.service.ReaderWriter;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class ReaderWriterImpl implements ReaderWriter {

    @Value("${app.locale}")
    private String locale;


    private final Scanner scanner = new Scanner(System.in);
    private final LocalizationService localizationService;

    @Override
    public void writeToConsole(String out) {
        System.out.println(localizationService.getMessage(out, locale));
    }

    @Override
    public String getString(String in) {
        return localizationService.getMessage(in, locale);
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }
}
