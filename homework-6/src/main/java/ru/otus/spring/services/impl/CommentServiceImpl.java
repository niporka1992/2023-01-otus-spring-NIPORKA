package ru.otus.spring.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.entities.Comment;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.CommentRepository;
import ru.otus.spring.services.CommentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            commentRepository.insert(new Comment(text, bookId));
            return "Комментарий добавлен.";
        }
        return "Книги с таким id нет";
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> findAll() {
        return commentRepository.getAll().stream().map(
                Comment::toString).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public String findById(long id) {
        Optional<Comment> comment = commentRepository.getById(id);
        if (comment.isEmpty()) {
            return "Такого комментария нет";
        }
        return comment.get().toString();
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> findByText(String text) {
        if (commentRepository.getByText(text).isEmpty()) {
            return List.of("Такого комментария нет");
        }
        return commentRepository.getByText(text).stream().map(Comment::toString).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public String updateById(long id, String text) {
        if (commentRepository.getById(id).isPresent()) {
            boolean result = commentRepository.updateById(id, text);
            return result ? "Комментарий обновлен." : "Ошибка";
        }
        return "Такой комментария нет";
    }

    @Transactional
    @Override
    public String deleteById(long id) {
        if (commentRepository.getById(id).isPresent()) {
            boolean result = commentRepository.deleteById(id);
            return result ? "Комментарий удален." : "Ошибка";
        }
        return "Такой комментария нет";
    }
}
