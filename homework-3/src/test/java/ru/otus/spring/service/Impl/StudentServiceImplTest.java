package ru.otus.spring.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.domain.Student;
import ru.otus.spring.service.IO.IOService;
import ru.otus.spring.service.StudentService;

@SpringBootTest
class StudentServiceImplTest {

    @MockBean
    IOService ioService;
    @Autowired
    StudentService studentService;

    @Test
    @DisplayName("Должен создавать студента.")
    void shouldReturnNewStudent() {
        Student student = studentService.determineCurrentStudent();
        Assertions.assertNotNull(student);
    }
}