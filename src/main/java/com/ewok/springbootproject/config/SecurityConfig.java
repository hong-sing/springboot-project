package com.ewok.springbootproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity  // Spring Security 설정들을 활성화시켜 준다.
public class SecurityConfig {

    @Bean
    public SecurityFilterChain newFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/h2-consol/**").permitAll()
                .anyRequest().permitAll();
        return http.build();
    }
}

/*
    csrf().disable()    https://velog.io/@woohobi/Spring-security-csrf%EB%9E%80
 */