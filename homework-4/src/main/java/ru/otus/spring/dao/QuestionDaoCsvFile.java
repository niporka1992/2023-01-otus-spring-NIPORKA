package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

@Component
@RequiredArgsConstructor
public class QuestionDaoCsvFile implements QuestionDao {

    private static final String SEPARATOR = ",";
    private final Resource resource;

    @Override
    public List<Question> findAllQuestions() throws IOException {
        return this.getAllQuestionsFromResource(resource);
    }

    private List<Question> getAllQuestionsFromResource(Resource resource) throws IOException {

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
        }
    }
}
