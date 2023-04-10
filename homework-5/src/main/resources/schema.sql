drop table if exists authors;
create table authors
(
    id      bigint auto_increment primary key,
    name    varchar(255),
    surname varchar(255)
);

drop table if exists genres;
create table genres
(
    id   bigint auto_increment primary key,
    name varchar(255)
);

drop table if exists books;
create table books
(
    id        bigint auto_increment primary key,
    name      varchar(255),
    author_id bigint,
    genre_id  bigint,
    foreign key (author_id) references authors (ID),
    foreign key (genre_id) references genres (ID)
);