package ru.otus.spring;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Caterpillar;

@MessagingGateway
@Component
public interface Transformer {
    @Gateway(requestChannel = "caterpillarsChannel", replyChannel = "butterfliesChannel")
    void process(Caterpillar caterpillar);
}
