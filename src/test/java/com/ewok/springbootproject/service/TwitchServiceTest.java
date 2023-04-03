package com.ewok.springbootproject.service;

import com.ewok.springbootproject.domain.token.TokenRepository;
import com.ewok.springbootproject.service.dto.Streamer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class TwitchServiceTest {

    @Autowired
    private TwitchService twitchService;

    @Autowired
    private TokenRepository tokenRepository;

//    @BeforeEach
//    public void setup() {
//        Token token = new Token("");
//        tokenRepository.save(token);
//    }

    @Test
    @DisplayName("액세스토큰_가져오기")
    public void getAccessToken() {
        //given
        String real = "";

        //when
        String at = twitchService.getAccessToken();

        //then
        assertThat(real).isEqualTo(at);
    }

    @Test
    @DisplayName("액세스토큰_유효성_검사")
    public void isAccessTokenValid() {
        //given
        String accessToken = twitchService.getAccessToken();

        //then
//        assertTrue(twitchService.isAccessTokenValid(accessToken));
        assertFalse(twitchService.isAccessTokenValid(accessToken));
    }

    @Test
    @DisplayName("액세스토큰_재발급")
    public void reGetAccessToken() {
        //when
        String reget = twitchService.reGetAccessToken();

        //then
        assertThat(reget).isEqualTo("");
    }

    @Test
    @DisplayName("스트리머_정보_가져오기")
    public void getStreamerInfo() {
        //given
        String login = "zilioner";

        //when
        Streamer streamer = new Streamer();
        streamer = twitchService.getStreamerInfo(login);

        //then
        assertThat(streamer.getDisplay_name()).isEqualTo("침착맨");
    }

}