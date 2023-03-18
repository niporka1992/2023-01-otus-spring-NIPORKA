package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Student;
import ru.otus.spring.service.StudentService;
import ru.otus.spring.service.l10n.LocalizationIOService;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final LocalizationIOService localizationIOService;

    @Override
    public Student determineCurrentStudent() {
        localizationIOService.outputMessage("string.askName");
        return new Student(localizationIOService.readString());
    }
}
