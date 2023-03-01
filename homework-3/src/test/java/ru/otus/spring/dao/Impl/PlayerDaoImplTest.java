package ru.otus.spring.dao.Impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerDaoImplTest {

    Player player = new Player();

    @Test
    @DisplayName("Сеттер должен устанавливать имя игрока.")
    void setName() {
        player.setName("player");
        assertEquals("player", player.getName());
    }
}