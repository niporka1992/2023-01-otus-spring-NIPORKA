package ru.otus.spring.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.spring.entities.User;
import ru.otus.spring.repositories.UserRepository;
import ru.otus.spring.services.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found - " + username));

        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("user"));

        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
    }
}
