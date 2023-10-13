package ru.otus.spring.health;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.otus.spring.services.BookService;

@Component
@RequiredArgsConstructor
public class BooksCountHealthIndicator implements HealthIndicator {
    private static final Logger logger = LoggerFactory.getLogger(BooksCountHealthIndicator.class);
    private static final long MIN = 2L;

    private final BookService bookService;

    @Override
    public Health health() {
        if (bookService.count() < MIN) {
            logger.error("MyHealthCheck is DOWN"); //
            return Health.down().withDetail("Минимальное кол-во  возможное для жизни приложения 2  ", "Кол - во книг меньше возможного ").build();
        } else {
            return Health.up().build();
        }
    }
}
