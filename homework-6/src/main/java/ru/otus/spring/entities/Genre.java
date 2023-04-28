package ru.otus.spring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "genres")
@Transactional

public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = true)
    private String name;

    public Genre(String name) {
        this.name = name;
    }
}

