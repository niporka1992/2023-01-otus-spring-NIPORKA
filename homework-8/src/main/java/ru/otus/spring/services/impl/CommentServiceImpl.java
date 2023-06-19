package ru.otus.spring.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    @Override
    public String save(String text, String bookId) {
        return bookRepository.findById(bookId).map(x -> {
            Book book = new Book(bookId);
            commentRepository.save(new Comment(text, book));
            return "Комментарий добавлен.";
        }).orElse("Книги с таким ID нет");
    }


    @Override
    public String findById(String id) {
        return commentRepository.findById(id)
                .map(Comment::toString)
                .orElse("Такого комментария нет");
    }

    @Override
    public void updateById(String id, String text) {
        var comment = new Comment(id, text);
        commentRepository.save(comment);

    }

    @Override
    public void deleteById(String id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> findByBookId(String bookId) {
        return commentRepository.findByBookId(bookId);
    }
}
