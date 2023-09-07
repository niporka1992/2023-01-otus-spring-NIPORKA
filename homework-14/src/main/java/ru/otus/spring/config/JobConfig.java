package ru.otus.spring.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.lang.NonNull;
import org.springframework.transaction.PlatformTransactionManager;
import ru.otus.spring.entities.Author;
import ru.otus.spring.entities.Book;
import ru.otus.spring.entities.Comment;
import ru.otus.spring.entities.Genre;

import javax.sql.DataSource;
import java.util.HashMap;

@Slf4j
@SuppressWarnings("unused")
@Configuration
@RequiredArgsConstructor
public class JobConfig {

    private final DataSource dataSource;

    private final JobRepository jobRepository;

    private final MongoTemplate mongoTemplate;

    private final PlatformTransactionManager transactionManager;


    @StepScope
    @Bean
    public MongoItemReader<Author> authorMongoItemReader(MongoTemplate mongoTemplate) {
        return new MongoItemReaderBuilder<Author>()
                .name("authorItemReader")
                .template(mongoTemplate)
                .targetType(Author.class)
                .jsonQuery("{}")
                .sorts(new HashMap<>())
                .build();
    }

    @StepScope
    @Bean
    public JdbcBatchItemWriter<Author> authorJdbcBatchItemWriter() {
        JdbcBatchItemWriter<Author> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO authors (id,name,surname) VALUES (:id,:name,:surname)");
        writer.setDataSource(dataSource);
        return writer;
    }

    @StepScope
    @Bean
    public MongoItemReader<Genre> genreMongoItemReader(MongoTemplate mongoTemplate) {
        return new MongoItemReaderBuilder<Genre>()
                .name("genreMongoItemReader")
                .template(mongoTemplate)
                .targetType(Genre.class)
                .jsonQuery("{}")
                .sorts(new HashMap<>())
                .build();
    }

    @StepScope
    @Bean
    public JdbcBatchItemWriter<Genre> genreJdbcBatchItemWriter() {
        JdbcBatchItemWriter<Genre> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO genres (id,name) VALUES (:id,:name)");
        writer.setDataSource(dataSource);
        return writer;
    }

    @StepScope
    @Bean
    public MongoItemReader<Book> bookMongoItemReader(MongoTemplate mongoTemplate) {
        return new MongoItemReaderBuilder<Book>()
                .name("bookMongoItemReader")
                .template(mongoTemplate)
                .targetType(Book.class)
                .jsonQuery("{}")
                .sorts(new HashMap<>())
                .build();
    }

    @StepScope
    @Bean
    public JdbcBatchItemWriter<Book> bookJdbcBatchItemWriter() {
        JdbcBatchItemWriter<Book> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO books (id,name,author_id,genre_id) VALUES (:id,:name,:author.id,:genre.id)");
        writer.setDataSource(dataSource);
        return writer;
    }

    @StepScope
    @Bean
    public MongoItemReader<Comment> commentMongoItemReader(MongoTemplate mongoTemplate) {
        return new MongoItemReaderBuilder<Comment>()
                .name("commentMongoItemReader")
                .template(mongoTemplate)
                .targetType(Comment.class)
                .jsonQuery("{}")
                .sorts(new HashMap<>())
                .build();
    }

    @StepScope
    @Bean
    public JdbcBatchItemWriter<Comment> commentJdbcBatchItemWriter() {
        JdbcBatchItemWriter<Comment> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO comments (id,text,book_id) VALUES (:id,:text,:book.id)");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public Step authorStep() {
        return new StepBuilder("authorStep", jobRepository)
                .<Author, Author>chunk(2, transactionManager)
                .reader(authorMongoItemReader(mongoTemplate))
                .writer(authorJdbcBatchItemWriter())
                .allowStartIfComplete(true)
                .listener(new ItemReadListener<>() {
                    @Override
                    public void afterRead(@NonNull Author author) {
                        log.info("Author with name : {} {} was read", author.getName(), author.getSurname());
                    }
                })
                .listener(new ItemWriteListener<>() {
                    @Override
                    public void afterWrite(@NonNull Chunk<? extends Author> items) {
                        items.forEach(author -> log.info("Author with name : {} {} was written", author.getName(), author.getSurname()));
                    }
                })
                .build();
    }

    @Bean
    public Step genreStep() {
        return new StepBuilder("genreStep", jobRepository)
                .<Genre, Genre>chunk(2, transactionManager)
                .reader(genreMongoItemReader(mongoTemplate))
                .writer(genreJdbcBatchItemWriter())
                .allowStartIfComplete(true)
                .listener(new ItemReadListener<>() {
                    @Override
                    public void afterRead(@NonNull Genre x) {
                        log.info("Genre with name : {} was read", x.getName());
                    }
                })
                .listener(new ItemWriteListener<>() {
                    @Override
                    public void afterWrite(@NonNull Chunk<? extends Genre> genres) {
                        genres.forEach(genre -> log.info("Genre with name : {} was written", genre.getName()));
                    }
                })
                .build();
    }

    @Bean
    public Step bookStep() {
        return new StepBuilder("bookStep", jobRepository)
                .<Book, Book>chunk(2, transactionManager)
                .reader(bookMongoItemReader(mongoTemplate))
                .writer(bookJdbcBatchItemWriter())
                .allowStartIfComplete(true)
                .listener(new ItemReadListener<>() {
                    @Override
                    public void afterRead(@NonNull Book book) {
                        log.info("Book with name : {} was read", book.getName());
                    }
                })
                .listener(new ItemWriteListener<>() {
                    @Override
                    public void afterWrite(@NonNull Chunk<? extends Book> books) {
                        books.forEach(book -> log.info("Book with name : {} was written", book.getName()));
                    }
                })
                .build();
    }

    @Bean
    public Step commentStep() {
        return new StepBuilder("commentStep", jobRepository)
                .<Comment, Comment>chunk(2, transactionManager)
                .reader(commentMongoItemReader(mongoTemplate))
                .writer(commentJdbcBatchItemWriter())
                .allowStartIfComplete(true)
                .listener(new ItemReadListener<>() {
                    @Override
                    public void afterRead(@NonNull Comment comment) {
                        log.info("Comment for book with name : {} was read", comment.getBook().getName());
                    }
                })
                .listener(new ItemWriteListener<>() {
                    @Override
                    public void afterWrite(@NonNull Chunk<? extends Comment> comments) {
                        comments.forEach(comment -> log.info("Comment for book with name : {} was written", comment.getBook().getName()));
                    }
                })
                .build();
    }

    @Bean
    public Job migrateMongoToMyPg(Step authorStep, Step genreStep, Step bookStep, Step commentStep) {
        return new JobBuilder("migrateMongoToMyPg", jobRepository)
                .start(authorStep)
                .next(genreStep)
                .next(bookStep)
                .next(commentStep)
                .build();
    }
}