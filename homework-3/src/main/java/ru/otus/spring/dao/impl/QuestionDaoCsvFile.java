package ru.otus.spring.dao.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionResourceNameProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

@Component
@RequiredArgsConstructor
public class QuestionDaoCsvFile implements QuestionDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionDao.class);
    private static final String SEPARATOR = ";";
    private final QuestionResourceNameProvider questionResourceNameProvider;
    private final ResourceLoader resourceLoader;

    @Override
    public List<Question> findAllQuestions() {
        var resourceName = questionResourceNameProvider.getResourceName();
        try (InputStream resourceAsStream = resourceLoader.getResource(resourceName).getInputStream()) {
            InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            return reader.lines()
                    .map(line -> {
                        String[] csvRecord = line.split(SEPARATOR);
                        return new Question(csvRecord[1].strip(), csvRecord[2].strip());
                    })
                    .collect(toCollection(ArrayList::new));
        } catch (IOException exception) {
            LOGGER.error("Resource " + resourceName + " is not available");
        }
        return Collections.emptyList();
    }
}




