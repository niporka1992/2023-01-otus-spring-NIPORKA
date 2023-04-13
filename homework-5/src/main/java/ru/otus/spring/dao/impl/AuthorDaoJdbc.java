package ru.otus.spring.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public long count() {
        Integer count = namedParameterJdbcOperations.getJdbcOperations().queryForObject(
                "select count(*) from authors", Integer.class);
        return count == null ? 0 : count;
    }

    @Override
    public Author insert(Author author) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("name", author.name());
        params.addValue("surname", author.surname());

        namedParameterJdbcOperations.update(
                "insert into authors (name, surname) values ( :name, :surname)", params, keyHolder);

        long id = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return new Author(id, author.name(), author.surname());
    }

    @Override
    public Author updateById(long id, String name, String surname) {
        Map<String, Object> params = Map.of("id", id, "name", name, "surname", surname);
        namedParameterJdbcOperations.update(
                "update authors set name = :name, surname = :surname where id = :id", params);
        return new Author(id, name, surname);
    }

    @Override
    public Author getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select id, name, surname from authors where id = :id", params, new AuthorMapper());
    }

    @Override
    public Author getByNameAndSurname(String name, String surname) {
        Map<String, Object> params = Map.of("name", name, "surname", surname);
        return namedParameterJdbcOperations.queryForObject(
                "select id, name, surname from authors where name = :name and surname = :surname", params, new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return namedParameterJdbcOperations.query("select id, name, surname from authors", new AuthorMapper());
    }


    @Override
    public long deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.update(
                "delete from authors where id = :id", params);
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            return new Author(id, name, surname);
        }
    }
}
