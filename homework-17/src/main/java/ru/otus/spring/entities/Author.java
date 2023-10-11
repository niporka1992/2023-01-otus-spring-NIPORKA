package ru.otus.spring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("authors")
public class Author {
    @Id
    private String id;

    private String name;

    private String surname;

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
