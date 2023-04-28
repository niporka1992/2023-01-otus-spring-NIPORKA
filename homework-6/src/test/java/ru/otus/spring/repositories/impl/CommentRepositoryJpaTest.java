package ru.otus.spring.repositories.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.entities.Comment;
import ru.otus.spring.repositories.CommentRepository;

import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("Тесты JPA для работы с комментариями")
@Import(CommentRepositoryJpa.class)
@DataJpaTest
class CommentRepositoryJpaTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("должен создать комментарий")
    void insert() {
        Comment expected = new Comment("text", 1);
        commentRepository.insert(expected);
        var actual = commentRepository.getById(5).get();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("должен обновить комментарий")
    void updateById() {
        Comment expected = new Comment(2, "text2", 1);
        commentRepository.updateById(1, "author2");
        Comment actual = commentRepository.getById(2).get();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("должен достать комментарий из библиотеки")
    void getById() {
        Comment expected = commentRepository.getById(1).get();
        assertThat(1).isEqualTo(expected.getId());
    }

    @Test
    @DisplayName("должен достать комментарий по тексту")
    void getByText() {
        Comment expected = commentRepository.getByText("text").get(0);
        assertThat(1).isEqualTo(expected.getId());
    }

    @Test
    @DisplayName("должен достать все комментарии из библиотеки")
    void getAll() {
        var commentList = commentRepository.getAll();
        Assertions.assertEquals(4, commentList.size());
    }

    @Test
    @DisplayName("должен удалять комментарий из библиотеки")
    void deleteById() {
        commentRepository.deleteById(1);
        assertThatThrownBy(() -> commentRepository.getById(1).get()).isInstanceOf(NoSuchElementException.class);
    }
}