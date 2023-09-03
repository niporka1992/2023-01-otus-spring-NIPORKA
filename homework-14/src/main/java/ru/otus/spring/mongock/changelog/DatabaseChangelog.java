package ru.otus.spring.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.entities.Author;
import ru.otus.spring.entities.Book;
import ru.otus.spring.entities.Comment;
import ru.otus.spring.entities.Genre;

@ChangeLog
public class DatabaseChangelog {

    private Book harryPotter;
    private Book parfumer;
    private Author roaling;
    private Author zuskind;
    private Genre fantasy;
    private Genre novel;

    @ChangeSet(order = "001", id = "dropDb", author = "nniporka")
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertAuthors", author = "nniporka")
    public void insertAuthors(MongockTemplate template) {
        roaling = template.save(new Author(1L, "Джоан", "Роулинг"));
        zuskind = template.save(new Author(2L, "Патрик", " Зюскинд"));
    }

    @ChangeSet(order = "003", id = "insertGenres", author = "nniporka")
    public void insertGenres(MongockTemplate template) {
        novel = template.save(new Genre(1L, "Novel"));
        fantasy = template.save(new Genre(2L, "Fantasy"));
    }

    @ChangeSet(order = "004", id = "insertBooks", author = "nniporka")
    public void insertBooks(MongockTemplate template) {
        harryPotter = template.save(new Book(1L, "Гарри Поттер", roaling,
                novel, null));
        parfumer = template.save(new Book(2L, "Парфюмер", zuskind, fantasy, null));
    }

    @ChangeSet(order = "005", id = "insertComments", author = "nniporka")
    public void insertComments(MongockTemplate template) {
        template.save(new Comment(1L, "Good book", harryPotter));
        template.save(new Comment(2L, "Perfect book", parfumer));
        template.save(new Comment(3L, "Good book2", harryPotter));
        template.save(new Comment(4L, "Perfect book3", parfumer));
    }

}