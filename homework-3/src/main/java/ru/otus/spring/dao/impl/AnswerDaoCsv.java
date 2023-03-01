package ru.otus.spring.dao.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.AnswerDao;
import ru.otus.spring.domain.Answer;

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
public class AnswerDaoCsv implements AnswerDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnswerDao.class);
    private static final String SEPARATOR = ",";
    private final Resource resource;

    @Override
    public List<Answer> findAllAnswers() {
        try (InputStream resourceAsStream = resource.getInputStream()) {

            InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            return reader.lines()
                    .map(line -> {
                        String[] csvRecord = line.split(SEPARATOR);
                        String answer = csvRecord[2].strip();
                        return new Answer(answer);
                    })
                    .collect(toCollection(ArrayList::new));
        } catch (IOException e) {
            LOGGER.error("Resource " + resource.getFilename() + " is not available");
        }
        return Collections.emptyList();
    }
}

