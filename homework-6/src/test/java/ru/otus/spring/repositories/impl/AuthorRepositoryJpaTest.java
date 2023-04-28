package ru.otus.spring.repositories.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.entities.Author;
import ru.otus.spring.repositories.AuthorRepository;

import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("Тесты JPA для работы с авторами")
@Import(AuthorRepositoryJpa.class)
@DataJpaTest
class AuthorRepositoryJpaTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DisplayName("должен положить автора в библиотеку")
    void insert() {
        Author expected = new Author("author", "author");
        authorRepository.insert(expected);
        var actual = authorRepository.getById(4).get();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("должен обновить автора в библиотеке")
    void updateById() {
        Author expected = new Author(2, "author2", "author2");
        authorRepository.updateById(2, "author2", "author2");
        Author actual = authorRepository.getById(2).get();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("должен достать автора из библиотеки")
    void getById() {
        Author expected = authorRepository.getById(1).get();
        assertThat(1).isEqualTo(expected.getId());
    }

    @Test
    @DisplayName("должен вернуть автора из библиотеки по ФИ")
    void getByNameAndSurname() {
        Author expected = new Author(2, "Лев", "Толстой");
        Author actual = authorRepository.getByNameAndSurname("Лев", "Толстой").get(0);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("должен вернуть список всех авторов из библиотеки")
    void getAll() {
        var authorList = authorRepository.getAll();
        Assertions.assertEquals(3, authorList.size());
    }

    @Test
    @DisplayName("должен удалить книгу из библиотеки")
    void deleteById() {
        authorRepository.deleteById(1);
        assertThatThrownBy(() -> authorRepository.getById(1).get()).isInstanceOf(NoSuchElementException.class);
    }
}