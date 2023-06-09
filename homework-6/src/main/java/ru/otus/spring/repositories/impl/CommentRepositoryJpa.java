package ru.otus.spring.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.entities.Comment;
import ru.otus.spring.exceptions.EntityNotFoundException;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryJpa implements CommentRepository {
    @PersistenceContext
    private final EntityManager em;
    private final BookRepository bookRepository;

    @Override
    public Comment insert(Comment comment) {
        if (comment.getId() == 0) {
            em.persist(comment);
            return comment;
        }
        return em.merge(comment);
    }

    @Override
    public void update(Comment comment) throws EntityNotFoundException {
        long id = comment.getId();
        Comment currentComment = getById(id).orElseThrow(() -> new EntityNotFoundException("Комментария не существует"));
        currentComment.setText(comment.getText());
        em.merge(currentComment);
    }

    @Override
    public Optional<Comment> getById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public void deleteById(long id) throws EntityNotFoundException {
        Comment comment = getById(id).orElseThrow(() -> new EntityNotFoundException("Комментария не существует"));
        em.remove(comment);
    }

    @Override
    public List<Comment> getCommentsByBookId(long bookId) {
        var book = bookRepository.getById(1).orElseThrow(() -> new EntityNotFoundException("Книги не существует"));
        TypedQuery<Comment> query = em.createQuery("select c " +
                "from Comment c " +
                "where c.book.id = :id", Comment.class);
        query.setParameter("id", bookId);
        return query.getResultList();
    }
}
