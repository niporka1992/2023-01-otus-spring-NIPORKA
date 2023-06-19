package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.entities.Comment;
import ru.otus.spring.services.CommentService;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommentCommands {
    private final CommentService commentService;

    @ShellMethod(value = "Создать коммент ", key = {"add comment", "ac"})
    public String addComment(@ShellOption(value = {"text"}) String name,
                             @ShellOption(value = {"bookId"}) String bookId) {

        return commentService.save(name, bookId);
    }

    @ShellMethod(value = "Показать комментарий по id ", key = {"get comments by id", "gcid"})
    public String getCommentById(@ShellOption(value = {"id"}) String id) {
        return commentService.findById(id);
    }

    @ShellMethod(value = "Удалить комментарий по id ", key = {"delete comments by id", "dcid"})
    public void deleteCommentById(@ShellOption(value = {"id"}) String id) {
        commentService.deleteById(id);
    }

    @ShellMethod(value = "Показать комментарий по id книги ", key = {"get comments by book id", "gcbid"})
    public String getCommentByBookId(@ShellOption(value = {"id"}) String bookId) {
        var listComments = commentService.findByBookId(bookId);
        return getCommentsAsString(listComments);
    }

    private String getCommentsAsString(List<Comment> comments) {
        if (comments.isEmpty()) {
            return "";
        }
        return format("Список комментариев книги:\n\t%s", comments.stream()
                .map(Comment::toString)
                .collect(Collectors.joining("\n\t"))
        );
    }
}