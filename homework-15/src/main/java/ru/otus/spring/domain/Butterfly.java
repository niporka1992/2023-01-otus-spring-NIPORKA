package ru.otus.spring.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Butterfly {
    private final Long id;
    private final LocalDateTime bornDateTime;

    public Butterfly(Long id) {
        this.id = id;
        this.bornDateTime = LocalDateTime.now();
    }
}
