package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Answer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

@Component
@RequiredArgsConstructor
public class AnswerDaoCsvFile implements AnswerDao {

    private static final String SEPARATOR = ",";
    private final Resource resource;

    @Override
    public List<Answer> findAllAnswers() throws IOException {
        return this.getAllAnswersFromResource(resource);
    }

    private List<Answer> getAllAnswersFromResource(Resource resource) throws IOException {

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
        }
    }
}
