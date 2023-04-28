package ru.otus.spring.repositories.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.entities.Genre;
import ru.otus.spring.repositories.GenreRepository;

import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("Тесты JPA для работы с жанрами")
@DataJpaTest
@Import(GenreRepositoryJpa.class)
class GenreRepositoryJpaTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("должен положить жанр в библиотеку")
    void insert() {
        Genre actual = genreRepository.insert(new Genre("Детектив")).get();
        Assertions.assertEquals(genreRepository.getById(3).get(), actual);
    }

    @Test
    @DisplayName("должен обновить жанр в библиотеке")
    void updateById() {
        Genre expected = new Genre(2, "Детектив");
        genreRepository.updateById(2, "Детектив");
        Genre actual = genreRepository.getById(2).get();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("должен достать жанр из библиотеки")
    void getById() {
        Genre expected = genreRepository.getById(1).get();
        assertThat(1).isEqualTo(expected.getId());
    }

    @Test
    @DisplayName("должен достать жанр из библиотеки по имени")
    void getByName() {
        Genre expected = genreRepository.getByName("Роман").get(0);
        assertThat(2).isEqualTo(expected.getId());
    }

    @Test
    @DisplayName("должен достать все жанры из библиотеки")
    void getAll() {
        var genreList = genreRepository.getAll();
        Assertions.assertEquals(2, genreList.size());
    }

    @Test
    @DisplayName("должен удалить жанр из библиотеки")
    void deleteById() {
        genreRepository.deleteById(1);
        assertThatThrownBy(() -> genreRepository.getById(1).get()).isInstanceOf(NoSuchElementException.class);
    }
}