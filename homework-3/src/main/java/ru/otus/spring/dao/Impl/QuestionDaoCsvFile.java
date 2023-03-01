package ru.otus.spring.dao.Impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

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
    private static final String SEPARATOR = ",";
    private final Resource resource;

    @Override
    public List<Question> findAllQuestions() {
        try (InputStream resourceAsStream = resource.getInputStream()) {
            InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            return reader.lines()
                    .map(line -> {
                        String[] csvRecord = line.split(SEPARATOR);
                        String question = csvRecord[1].strip();
                        return new Question(question);
                    })
                    .collect(toCollection(ArrayList::new));
        } catch (IOException exception) {
            LOGGER.error("Resource " + resource.getFilename() + " is not available");
        }
        return Collections.emptyList();
    }
}




