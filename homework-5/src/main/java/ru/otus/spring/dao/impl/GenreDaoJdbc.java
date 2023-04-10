package ru.otus.spring.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public long count() {
        Integer count = namedParameterJdbcOperations.getJdbcOperations().queryForObject(
                "select count(*) from genres", Integer.class);

        return count == null ? 0 : count;
    }

    @Override
    public long insert(Genre genre) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", genre.id());
        params.addValue("name", genre.name());

        namedParameterJdbcOperations.update(
                "insert into genres (id,name) values (:id,:name)", params, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public long updateById(long id, Genre genre) {
        Map<String, Object> params = Map.of("id", id, "name", genre.name());

        return namedParameterJdbcOperations.update(
                "update genres set name = :name, surname = :surname where id = :id", params);
    }

    @Override
    public Genre getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);

        return namedParameterJdbcOperations.queryForObject(
                "select id, name from genres where id = :id", params, new GenreMapper());
    }

    @Override
    public Genre getByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);

        return namedParameterJdbcOperations.queryForObject(
                "select id, name from genres where name = :name", params, new GenreMapper());
    }

    @Override
    public List<Genre> getAll() {
        return namedParameterJdbcOperations.query("select id, name, surname from genres", new GenreMapper());
    }

    @Override
    public long deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);

        return namedParameterJdbcOperations.update(
                "delete from genres where id = :id", params);
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            return new Genre(id, name);
        }
    }
}
