package ru.otus.spring.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.entities.Comment;
import ru.otus.spring.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryJpa implements CommentRepository {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public Comment insert(Comment comment) {
        if (comment.getId() == 0) {
            em.persist(comment);
            return comment;
        }
        return em.merge(comment);
    }

    @Override
    public boolean updateById(long id, String text) {
        var query = em.createQuery("update Comment c set c.text= :text where c.id= :id");
        query.setParameter("id", id);
        query.setParameter("text", text);
        return query.executeUpdate() == 1;
    }

    @Override
    public Optional<Comment> getById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public List<Comment> getByText(String text) {
        var query = em.createQuery(
                "select c from Comment c where c.text= :text", Comment.class);
        query.setParameter("text", text);
        return query.getResultList();
    }

    @Override
    public List<Comment> getAll() {
        TypedQuery<Comment> query = em.createQuery("SELECT c FROM Comment c", Comment.class);
        return query.getResultList();
    }

    @Override
    public boolean deleteById(long id) {
        var query = em.createQuery("delete from Comment c where c.id= :id");
        query.setParameter("id", id);
        return query.executeUpdate() == 1;
    }
}
