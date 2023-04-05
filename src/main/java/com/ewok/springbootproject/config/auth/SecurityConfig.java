package com.ewok.springbootproject.config.auth;

import com.ewok.springbootproject.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity  // Spring Security 설정들을 활성화시켜 준다.
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain newFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/**").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                .requestMatchers("/h2-consol/**", "/profile").permitAll()
                .anyRequest().permitAll()
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .loginPage("/loginForm")
                .userInfoEndpoint().userService(customOAuth2UserService);
        return http.build();
    }
}

/*
    csrf().disable()    https://velog.io/@woohobi/Spring-security-csrf%EB%9E%80
 */