package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Student;
import ru.otus.spring.service.IO.IOService;
import ru.otus.spring.service.StudentService;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final IOService ioService;

    @Override
    public Student determineCurrentStudent() {

        return new Student(ioService.readString());
    }
}
