package ru.otus.spring.event;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.otus.spring.entities.Book;
import ru.otus.spring.entities.Comment;
import ru.otus.spring.repositories.CommentRepository;

@Component
@RequiredArgsConstructor
public class MongoBookCascadeEventsListener extends AbstractMongoEventListener<Book> {

    private final CommentRepository commentRepository;
    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Book> event) {
        super.onAfterDelete(event);
        var bookId = event.getSource().get("_id").toString();
        Book book = new Book(bookId);

        Criteria criteria = Criteria.where("book").is(book);
        Query query = new Query(criteria);

        Mono<Long> countCommentsMono = mongoTemplate.count(query, Comment.class);

        countCommentsMono
                .filter(count -> count < 1)
                .flatMap(count -> commentRepository.deleteAllByBook(book))
                .subscribe();
    }
}
