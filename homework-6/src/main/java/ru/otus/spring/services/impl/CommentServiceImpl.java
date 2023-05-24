package ru.otus.spring.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.entities.Book;
import ru.otus.spring.entities.Comment;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.CommentRepository;
import ru.otus.spring.services.CommentService;

@Service
@RequiredArgsConstructor
@Transactional

public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Transactional
    @Override
    public String save(String text, long bookId) {
        if (bookRepository.getById(bookId).isPresent()) {
            Book book = new Book(bookId);
            commentRepository.insert(new Comment(text, book));
            return "Комментарий добавлен.";
        }
        return "Книги с таким id нет";
    }


    @Transactional(readOnly = true)
    @Override
    public String findById(long id) {
        return commentRepository.getById(id)
                .map(comment -> "Комментарий - " + comment)
                .orElse("Такого комментария нет");
    }
    @Transactional
    @Override
    public String updateById(long id, String text) {
        if (commentRepository.getById(id).isPresent()) {
            commentRepository.updateById(id, text);
            return "Комментарий обновлен.";
        }
        return "Такой комментария нет";
    }

    @Transactional
    @Override
    public String deleteById(long id) {
        if (commentRepository.getById(id).isPresent()) {
               commentRepository.deleteById(id);
            return  "Комментарий удален.";
        }
        return "Такой комментария нет";
    }
}
