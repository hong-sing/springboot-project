package com.ewok.springbootproject.domain.token;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String token;

    public Token(String token) {
        this.token = token;
    }

    public void update(String token) {
        this.token = token;
    }
}
