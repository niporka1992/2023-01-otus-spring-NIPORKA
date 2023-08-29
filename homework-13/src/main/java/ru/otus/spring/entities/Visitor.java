package ru.otus.spring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Document("user")
@Data
public class Visitor {
    @Id
    private String id;
    private String name;
    private String password;
    private String email;
    private List<String> roles;

    public Visitor(String name, String password, List<String> roles) {
        this.name = name;
        this.password = password;
        this.roles = roles;
    }
}
