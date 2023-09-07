package ru.otus.spring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "genres")
@Data
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String mId;

    @Column(name = "name", unique = true)
    private String name;

    public Genre(String name) {
        this.name = name;
    }

    public Genre(String mId, String name) {
        this.mId = mId;
        this.name = name;
    }
}

