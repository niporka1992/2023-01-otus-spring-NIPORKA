package ru.otus.spring.service.IO.impl;

import org.springframework.stereotype.Component;
import ru.otus.spring.service.IO.IOService;

import java.io.PrintStream;
import java.util.Scanner;

@Component
public class IOServiceStreams implements IOService {

    private final PrintStream output;
    private final Scanner scanner;

    public IOServiceStreams() {
        this.output = System.out;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String readString() {
        return scanner.next();
    }

    @Override
    public void outputString(String s) {
        output.println(s);
    }
}