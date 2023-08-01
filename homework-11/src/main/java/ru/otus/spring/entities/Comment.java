package ru.otus.spring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Document("comments")
@Data
public class Comment {
    @Id
    private String id;

    private String text;
    @DBRef
    private Book book;

    public Comment(String id, String text) {
        this.id = id;
        this.text = text;
    }
}
