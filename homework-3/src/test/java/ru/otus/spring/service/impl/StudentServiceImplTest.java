package ru.otus.spring.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.domain.Student;
import ru.otus.spring.service.StudentService;
import ru.otus.spring.service.l10n.LocalizationIOService;

@SpringBootTest
class StudentServiceImplTest {

    @MockBean
    private LocalizationIOService localizationIOService;
    @Autowired
    private StudentService studentService;

    @Test
    @DisplayName("Должен создавать студента.")
    void shouldReturnNewStudent() {
        Student student = studentService.determineCurrentStudent();
        Assertions.assertNotNull(student);
    }
}