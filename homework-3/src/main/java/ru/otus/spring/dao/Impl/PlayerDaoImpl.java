package ru.otus.spring.dao.Impl;

import org.springframework.stereotype.Component;
import ru.otus.spring.dao.PlayerDao;
import ru.otus.spring.domain.Player;


@Component
public class PlayerDaoImpl implements PlayerDao {
    private final Player player = new Player();

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public void setName(String name) {
        player.setName(name);
    }
}
