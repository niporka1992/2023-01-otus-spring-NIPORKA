package ru.otus.spring.service.IO.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.IO.IOService;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class OpenedStreamsIOService implements IOService {
    private final PrintStream out;
    private final Scanner sc;

    public OpenedStreamsIOService(@Value("#{ T(java.lang.System).in}") InputStream in,
                                  @Value("#{ T(java.lang.System).out}") PrintStream out) {
        this.out = out;
        this.sc = new Scanner(in);
    }

    @Override
    public String readString() {
        return sc.next();
    }

    @Override
    public void outputString(String s) {
        out.println(s);
    }
}