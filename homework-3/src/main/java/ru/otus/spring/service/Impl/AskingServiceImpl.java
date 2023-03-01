package ru.otus.spring.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.PlayerDao;
import ru.otus.spring.service.AskingService;
import ru.otus.spring.service.ReaderWriter;

@Service
@RequiredArgsConstructor
public class AskingServiceImpl implements AskingService {

    private final PlayerDao playerDao;
    private final ReaderWriter readerWriter;

    @Override
    public void greetPlayer() {
        readerWriter.writeToConsole("string.name");
        String playerName = readerWriter.read();
        playerDao.setName(playerName);
    }
}
