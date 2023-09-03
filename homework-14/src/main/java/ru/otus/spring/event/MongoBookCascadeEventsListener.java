package ru.otus.spring.event;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;
import ru.otus.spring.entities.Book;
import ru.otus.spring.entities.Comment;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MongoBookCascadeEventsListener extends AbstractMongoEventListener<Comment> {

    private final MongoTemplate mongoTemplate;

    @Override
    public void onAfterSave(AfterSaveEvent<Comment> event) {
        super.onAfterSave(event);
        Comment comment = event.getSource();
        Book book = comment.getBook();
        if (book.getComments() != null) {
            book.getComments().add(comment);
        } else {
            List<Comment> comments = new ArrayList<>();
            comments.add(comment);
            book.setComments(comments);
        }
        mongoTemplate.save(book);
    }
}