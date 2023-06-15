package ru.otus.spring.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.entities.Book;
import ru.otus.spring.entities.Comment;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.CommentRepository;
import ru.otus.spring.services.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Transactional
    @Override
    public String save(String text, long bookId) {
        return bookRepository.findById(bookId).map(x -> {
            Book book = new Book(bookId);
            commentRepository.save(new Comment(text, book));
            return "Комментарий добавлен.";
        }).orElse("Книги с таким ID нет");
    }


    @Transactional(readOnly = true)
    @Override
    public String findById(long id) {
        return commentRepository.findById(id)
                .map(Comment::toString)
                .orElse("Такого комментария нет");
    }

    @Transactional
    @Override
    public void updateById(long id, String text) {
        var comment = new Comment(id, text);
        commentRepository.save(comment);

    }

    @Transactional
    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> findByBookId(long bookId) {
        return commentRepository.findByBookId(bookId);
    }
}
