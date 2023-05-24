package ru.otus.spring.repositories.impl;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.entities.Book;
import ru.otus.spring.entities.Comment;
import ru.otus.spring.repositories.CommentRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Тесты JPA для работы с комментариями")
@Import(CommentRepositoryJpa.class)
@DataJpaTest
class CommentRepositoryJpaTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("должен создать комментарий")
    void insert() {
        Comment expected = new Comment("text", new Book(1));
        commentRepository.insert(expected);
        val actual = em.find(Comment.class, 5);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("должен обновить комментарий")
    void updateById() {
        Comment expected = new Comment(2, "text2", new Book(1));
        commentRepository.updateById(1, "author2");
        val actual = em.find(Comment.class, 2);
        Assertions.assertThat(actual.getId()).isEqualTo(expected.getId());
        Assertions.assertThat(actual.getText()).isEqualTo(expected.getText());
    }

    @Test
    @DisplayName("должен достать комментарий из библиотеки")
    void getById() {
        val optionalActualComment = commentRepository.getById(1);
        val expectedComment = em.find(Comment.class, 1);
        Assertions.assertThat(optionalActualComment).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    @DisplayName("должен удалять комментарий из библиотеки")
    void deleteById() {
        commentRepository.deleteById(1);
        val comment = em.find(Comment.class, 1);
        assertNull(comment);
    }
}