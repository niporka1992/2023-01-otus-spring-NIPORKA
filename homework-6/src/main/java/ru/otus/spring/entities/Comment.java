package ru.otus.spring.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Data
@NoArgsConstructor
@Entity
@Table(name = "comments")
@Transactional

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "text")
    private String text;

    @Column(name = "book_id", nullable = false)
    private long bookId;

    public Comment(String text, long bookId) {
        this.text = text;
        this.bookId = bookId;
    }

    public Comment(long id, String text, long bookId) {
        this.id = id;
        this.text = text;
        this.bookId = bookId;
    }
}
