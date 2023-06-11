package ru.otus.spring.repositories.impl;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.entities.Genre;
import ru.otus.spring.repositories.GenreRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;


@DisplayName("Тесты JPA для работы с жанрами")
@DataJpaTest
@Import(GenreRepositoryJpa.class)
class GenreRepositoryJpaTest {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("должен положить жанр в библиотеку")
    void insert() {
        Genre expected = new Genre("Детектив");
        genreRepository.insert(expected);
        val actual = em.find(Genre.class, 3);
        org.assertj.core.api.Assertions.assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("должен обновить жанр в библиотеке")
    void updateById() {
        Genre expected = new Genre(2, "Детектив");
        genreRepository.updateById(2, "Детектив");
        val actual = em.find(Genre.class, 2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("должен достать жанр из библиотеки")
    void getById() {
        val optionalActualGenre = genreRepository.getById(1);
        val expectedGenre = em.find(Genre.class, 1);
        org.assertj.core.api.Assertions.assertThat(optionalActualGenre).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    @DisplayName("должен достать жанр из библиотеки по имени")
    void getByName() {
        val actualGenre = genreRepository.getByName("Роман").get(0);
        val expectedGenre = em.find(Genre.class, 2);
        org.assertj.core.api.Assertions.assertThat(actualGenre)
                .usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    @DisplayName("должен достать все жанры из библиотеки")
    void getAll() {
        val authorList = genreRepository.getAll();
        assertThat(authorList).isNotNull().hasSize(2)
                .allMatch(s -> !s.getName().equals(""));

    }

    @Test
    @DisplayName("должен удалить жанр из библиотеки")
    void deleteById() {
        genreRepository.deleteById(1);
        val genre = em.find(Genre.class, 1);
        assertNull(genre);
    }
}