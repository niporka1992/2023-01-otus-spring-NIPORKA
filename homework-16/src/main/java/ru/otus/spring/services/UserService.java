package ru.otus.spring.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.otus.spring.entities.Visitor;

public interface UserService extends UserDetailsService {
    void saveUser(Visitor user);
}
