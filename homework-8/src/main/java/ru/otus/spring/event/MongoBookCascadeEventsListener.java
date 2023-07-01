package ru.otus.spring.event;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.spring.entities.Book;
import ru.otus.spring.repositories.CommentRepository;

@Component
@RequiredArgsConstructor
public class MongoBookCascadeEventsListener extends AbstractMongoEventListener<Book> {

    private final CommentRepository commentRepository;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Book> event) {
        super.onAfterDelete(event);
        var bookId = event.getSource().get("_id").toString();
        Book book = new Book(bookId);
        if (commentRepository.countCommentByBook(book) < 1) {
            commentRepository.deleteAllByBook(book);
        }
    }
}
