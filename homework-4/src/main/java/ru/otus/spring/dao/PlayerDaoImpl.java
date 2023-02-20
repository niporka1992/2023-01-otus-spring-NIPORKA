package ru.otus.spring.dao;

import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Player;
import ru.otus.spring.service.ReaderWriter.ReaderWriterImpl;

@Component
public class PlayerDaoImpl implements PlayerDao {

    private final ReaderWriterImpl readerWriter = new ReaderWriterImpl();
    private final Player player = new Player();

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public void setName() {
        player.setName(readerWriter.read());
    }
}
