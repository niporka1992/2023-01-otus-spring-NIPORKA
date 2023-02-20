package ru.otus.spring.service.ReaderWriter;

import java.util.Scanner;

public class ReaderWriterImpl implements ReaderWriter {
    private final Scanner sc = new Scanner(System.in);

    @Override
    public void writeToConsole(String out) {
        System.out.println(out);
    }

    @Override
    public String read() {
        return sc.nextLine();
    }
}
