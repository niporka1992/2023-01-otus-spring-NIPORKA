package ru.otus.spring.service;

import ru.otus.spring.domain.Student;
import ru.otus.spring.domain.TestingResult;

public interface TestingService {
    void executeExam();

    TestingResult executeExamFor(Student student);

}
