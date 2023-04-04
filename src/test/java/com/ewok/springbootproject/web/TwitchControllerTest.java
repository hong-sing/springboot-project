package com.ewok.springbootproject.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TwitchControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    /*
    @Test
    void 게시글페이지_로딩() {
        //when
        String body = this.restTemplate.getForObject("/api/v1/search/nanajam777", String.class);

        //then
        assertThat(body).contains("예민하네");
    }
     */

}