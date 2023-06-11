DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS comments;

CREATE TABLE authors
(
    id      BIGINT NOT NULL AUTO_INCREMENT,
    name    VARCHAR(255),
    surname VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE genres
(
    id   BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) unique,
    PRIMARY KEY (id)
);

CREATE TABLE books
(
    id        BIGINT       NOT NULL AUTO_INCREMENT,
    name      VARCHAR(255) NOT NULL,
    author_id BIGINT       NOT NULL REFERENCES authors (id) ON DELETE CASCADE,
    genre_id  BIGINT       NOT NULL REFERENCES genres (id) ON DELETE CASCADE,
    PRIMARY KEY (id)
);

CREATE TABLE comments
(
    id      BIGINT NOT NULL AUTO_INCREMENT,
    book_id BIGINT NOT NULL REFERENCES books (id) ON DELETE CASCADE,
    text    VARCHAR(255),
    PRIMARY KEY (id)
);