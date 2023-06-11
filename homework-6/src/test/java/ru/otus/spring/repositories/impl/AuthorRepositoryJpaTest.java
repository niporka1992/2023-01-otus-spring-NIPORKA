package ru.otus.spring.repositories.impl;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.entities.Author;
import ru.otus.spring.repositories.AuthorRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Тесты JPA для работы с авторами")
@Import(AuthorRepositoryJpa.class)
@DataJpaTest
class AuthorRepositoryJpaTest {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("должен положить автора в библиотеку")
    void insert() {
        Author expected = new Author("author", "author");
        authorRepository.insertOrUpdate(expected);
        val actual = em.find(Author.class, 4);
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("должен обновить автора в библиотеке")
    void updateById() {
        Author expected = new Author(2, "author", "author");
        authorRepository.insertOrUpdate(expected);
        val actual = em.find(Author.class, 2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("должен достать автора из библиотеки")
    void getById() {
        val optionalActualAuthor = authorRepository.getById(1);
        val expectedAuthor = em.find(Author.class, 1);
        assertThat(optionalActualAuthor).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    @DisplayName("должен вернуть автора из библиотеки по ФИ")
    void getByNameAndSurname() {
        val expected = em.find(Author.class, 2);
        val actualListAuthor = authorRepository.getByNameAndSurname("Лев", "Толстой");
        Assertions.assertEquals(expected, actualListAuthor.get(0));
    }

    @Test
    @DisplayName("должен вернуть список всех авторов из библиотеки")
    void getAll() {
        val authorList = authorRepository.getAll();
        assertThat(authorList).isNotNull().hasSize(3)
                .allMatch(s -> !s.getName().equals(""))
                .allMatch(s -> !s.getSurname().equals(""));
    }

    @Test
    @DisplayName("должен удалить книгу из библиотеки")
    void deleteById() {
        authorRepository.deleteById(1);
        val author = em.find(Author.class, 1);
        assertNull(author);
    }
}