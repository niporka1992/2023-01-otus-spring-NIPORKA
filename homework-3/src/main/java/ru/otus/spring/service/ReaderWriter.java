package ru.otus.spring.service;

public interface ReaderWriter {

    void writeToConsole(String out);

    String getString(String in);

    String read();
}
