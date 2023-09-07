package ru.otus.spring.entities;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String mId;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    public Book(String name, Author author, Genre genre, List<Comment> comment) {
        this.name = name;
        this.genre = genre;
        this.author = author;
        this.comments = comment;
    }

    public Book(long id, String name, Author author, Genre genre, List<Comment> comment) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.comments = comment;
    }
    public Book(String id, String name, Author author, Genre genre, List<Comment> comment) {
        this.mId = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.comments = comment;
    }

    public Book(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' + "}";
    }
}
