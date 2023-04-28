package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.services.CommentService;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommentCommands {

    private final CommentService commentService;

    @ShellMethod(value = "Создать коммент ", key = {"add comment", "ac"})
    public String addComment(@ShellOption(value = {"text"}) String name,
                             @ShellOption(value = {"bookId"}) long bookId) {

        return commentService.save(name, bookId);
    }

    @ShellMethod(value = "Показать комментарий по id ", key = {"get comments by id", "gcid"})
    public String getCommentById(@ShellOption(value = {"id"}) long id) {
        return commentService.findById(id);
    }

    @ShellMethod(value = "Удалить комментарий по id ", key = {"delete comments by id", "dcid"})
    public String deleteCommentById(@ShellOption(value = {"id"}) long id) {
        return commentService.deleteById(id);
    }
}