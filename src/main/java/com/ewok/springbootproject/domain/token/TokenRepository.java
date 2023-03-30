package com.ewok.springbootproject.domain.token;

import com.ewok.springbootproject.domain.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Integer> {
}