package ru.otus.spring.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public long getCount() {
        Integer count = namedParameterJdbcOperations.getJdbcOperations().queryForObject("select count(*) from books", Integer.class);
        return count == null ? 0 : count;
    }

    @Override
    public Book insert(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("name", book.name());
        params.addValue("author_id", book.author().id());
        params.addValue("genre_id", book.genre().id());

        long id = namedParameterJdbcOperations.update(
                "insert into books (name,author_id,genre_id) values (:name,:author_id,:genre_id)", params, keyHolder);
        return new Book(id, book.name(), new Author(book.author().id()), new Genre(book.genre().id()));
    }

    @Override
    public Book updateById(long id, Book book) {
        Map<String, Object> params = Map.of("id", id, "name", book.name(), "author_id", book.author().id(), "genre_id", book.genre().id());
        namedParameterJdbcOperations.update(
                "update books set name = :name, author_id = :author_id, genre_id =:genre_id where id=:id", params);
        return new Book(id, book.name(), new Author(book.author().id()), new Genre(book.genre().id()));
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "SELECT books.id, books.name, books.author_id, books.genre_id, authors.name, authors.surname, genres.name " +
                        "FROM books " +
                        "LEFT JOIN authors ON books.author_id = authors.id " +
                        "LEFT JOIN genres ON books.genre_id = genres.id " +
                        "WHERE books.id = :id", params, new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query(
                "SELECT books.id, books.name, books.author_id,books.genre_id,authors.name,authors.surname, genres.name " +
                        "FROM books " +
                        "LEFT JOIN authors ON books.author_id = authors.id " +
                        "LEFT JOIN genres ON books.genre_id = genres.id ", new BookMapper());

    }

    @Override
    public long deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.update("delete from books where id = :id", params);
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            long bookId = rs.getLong("id");
            String bookName = rs.getString("name");

            long authorId = rs.getLong("author_id");
            String authorName = rs.getString("authors.name");
            String authorSurname = rs.getString("authors.surname");

            long genreId = rs.getLong("genre_id");
            String genreName = rs.getString("genres.name");

            return new Book(bookId, bookName, new Author(authorId, authorName, authorSurname), new Genre(genreId, genreName));
        }
    }
}
