package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Student;
import ru.otus.spring.domain.TestingResult;
import ru.otus.spring.service.IO.IOService;
import ru.otus.spring.service.ResultService;
import ru.otus.spring.service.StudentService;
import ru.otus.spring.service.TestingService;
import ru.otus.spring.service.l10n.LocalizationIOService;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TestingServiceImpl implements TestingService {
    private final IOService ioService;
    private final StudentService studentService;
    private final QuestionDao questionDao;
    private final LocalizationIOService localizationIOService;
    private final ResultService resultService;

    @Override
    public void executeExam() {
        localizationIOService.outputMessage("string.askName");
        var student = studentService.determineCurrentStudent();
        var examResult = executeExamFor(student);
        resultService.show(examResult);
    }


    public TestingResult executeExamFor(Student student) {

        var executeExamFor = questionDao.findAllQuestions();
        var correctlyAnsweredQuestions = new ArrayList<>();

        executeExamFor.forEach(question -> {
                    ioService.outputString(question.getName());
                    String answer = ioService.readString();
                    if (!question.getAnswer().equals(answer)) return;
                    correctlyAnsweredQuestions.add(question);
                }
        );

        return new TestingResult(student, executeExamFor.size(), correctlyAnsweredQuestions.size());
    }
}
