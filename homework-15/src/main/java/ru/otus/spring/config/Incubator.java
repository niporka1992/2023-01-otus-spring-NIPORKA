package ru.otus.spring.config;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.domain.Butterfly;
import ru.otus.spring.domain.Caterpillar;

@MessagingGateway
@SuppressWarnings("all")
public interface Incubator {
    @Gateway(requestChannel = "caterpillarsChannel", replyChannel = "butterfliesChannel")
    Butterfly process(Caterpillar caterpillar);
}
