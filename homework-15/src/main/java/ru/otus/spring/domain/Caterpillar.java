package ru.otus.spring.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Random;

@Data
public class Caterpillar {
    private final Long id;
    private final LocalDateTime bornDateTime;

    public Caterpillar() {
        Random random = new Random();
        this.id = random.nextLong();
        this.bornDateTime = LocalDateTime.now();
    }
}
