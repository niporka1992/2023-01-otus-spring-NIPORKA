package ru.otus.spring.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.entities.Author;
import ru.otus.spring.entities.Book;
import ru.otus.spring.entities.Comment;
import ru.otus.spring.entities.Genre;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.CommentRepository;
import ru.otus.spring.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ChangeLog
public class DatabaseChangelog {

    private final List<Author> authors;
    private final List<Genre> genres;
    private final List<Book> books;
    private final List<Comment> comments;


    public DatabaseChangelog() {

        authors = new ArrayList<>();
        authors.add(new Author("a0", "Джоан", "Роулинг"));
        authors.add(new Author("a1", "Лев", "Толстой"));
        authors.add(new Author("a2", "Патрик", "Зюскинд"));


        genres = new ArrayList<>();
        genres.add(new Genre("g0", "Фэнтези"));
        genres.add(new Genre("g1", "Роман"));

        comments = new ArrayList<>();
        comments.add(new Comment("g0", "text0", new Book("b0", "Гарри Поттер", authors.get(0), genres.get(0))));
        comments.add(new Comment("g1", "text1", new Book("b0", "Гарри Поттер", authors.get(0), genres.get(0))));
        comments.add(new Comment("g2", "text2", new Book("b1", "Война и мир", authors.get(1), genres.get(1))));


        books = new ArrayList<>();
        books.add(new Book("b0", "Гарри Поттер", authors.get(0), genres.get(0), Arrays.asList(comments.get(0), comments.get(1))));
        books.add(new Book("b1", "Война и мир", authors.get(1), genres.get(1), Collections.singletonList(comments.get(2))));
        books.add(new Book("b2", "Парфюмер", authors.get(2), genres.get(1), Collections.emptyList()));


    }

    @ChangeSet(order = "001", id = "dropDb", author = "niporka1992", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertAuthors", author = "niporka1992")
    public void insertAuthors(AuthorRepository repository) {
        repository.saveAll(authors);
    }

    @ChangeSet(order = "003", id = "insertGenres", author = "niporka1992")
    public void insertGenres(GenreRepository repository) {
        repository.saveAll(genres);
    }

    @ChangeSet(order = "004", id = "insertBooks", author = "niporka1992")
    public void insertBooks(BookRepository repository) {
        repository.saveAll(books);
    }

    @ChangeSet(order = "005", id = "insertComments", author = "niporka1992")
    public void insertComments(CommentRepository repository) {
        repository.saveAll(comments);
    }
}
