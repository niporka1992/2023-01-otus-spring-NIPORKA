package ru.otus.spring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String mId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    public Author(String id, String name, String surname) {
        this.mId = id;
        this.name = name;
        this.surname = surname;
    }
}
