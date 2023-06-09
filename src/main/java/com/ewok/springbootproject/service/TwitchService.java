package com.ewok.springbootproject.service;
import com.ewok.springbootproject.domain.posts.PostsRepository;
import com.ewok.springbootproject.domain.token.Token;
import com.ewok.springbootproject.domain.token.TokenRepository;
import com.ewok.springbootproject.service.dto.AccessTokenResponse;
import com.ewok.springbootproject.service.dto.StreamInfo;
import com.ewok.springbootproject.service.dto.StreamInfoData;
import com.ewok.springbootproject.service.dto.Streamer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Service
public class TwitchService {

    private final TokenRepository tokenRepository;
    private final PostsRepository postsRepository;

    @Value("${spring.security.oauth2.client.registration.twitch.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.twitch.client-secret}")
    private String clientSecret;

    @Transactional
    public String getAccessToken() {
        Token token = tokenRepository.findById(1).orElseThrow(() -> new IllegalArgumentException("AccessToken이 없습니다."));
        String value = token.getToken();
        return value;
    }

    public boolean isAccessTokenValid(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    "https://id.twitch.tv/oauth2/validate",
                    HttpMethod.GET,
                    entity,
                    String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return true;
            } else {
                throw new RuntimeException("Unexpected response from Twitch API");
            }
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                return false;
            } else {
                throw new RuntimeException("Unexpected error from Twitch API", e);
            }
        }
    }

    @Transactional
    public String reGetAccessToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://id.twitch.tv/oauth2/token")
                .queryParam("client_id", clientId)
                .queryParam("client_secret", clientSecret)
                .queryParam("grant_type", "client_credentials");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AccessTokenResponse> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, AccessTokenResponse.class);
        String newToken = response.getBody().getAccess_token();

        // 재발급 받은 토큰 update
        Token tokenEntity = tokenRepository.findById(1).orElseThrow(() -> new IllegalArgumentException("토큰이 없습니다"));
        tokenEntity.update(newToken);

        return newToken;
    }

    public Streamer getStreamerInfo(String login) {

        // 토큰이 유효하지 않다면 재발급
        String token = getAccessToken();
        if (!isAccessTokenValid(token)) {
            token = reGetAccessToken();
        }

        // 정보 요청
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.set("Client-Id", clientId);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.twitch.tv/helix/users")
                .queryParam("login", login);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<LinkedHashMap> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, LinkedHashMap.class);
        LinkedHashMap data = response.getBody();
        ArrayList list = (ArrayList) data.get("data");
        LinkedHashMap info = (LinkedHashMap) list.get(0);
        Streamer streamer = new Streamer(info);
        return streamer;
    }

    public List<StreamInfoData> getStreamInfo(String language) {
        // 토큰이 유효하지 않다면 재발급
        String token = getAccessToken();
        if (!isAccessTokenValid(token)) {
            token = reGetAccessToken();
        }

        // 정보 요청
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.set("Client-Id", clientId);
        headers.setBearerAuth(token);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.twitch.tv/helix/streams")
                .queryParam("language", language);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<LinkedHashMap> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, LinkedHashMap.class);
        LinkedHashMap data = response.getBody();
        StreamInfo streamInfo = new StreamInfo(data);
        List<StreamInfoData> infoData = streamInfoToStreamInfoData(streamInfo);
        return infoData;
    }

    public List<StreamInfoData> streamInfoToStreamInfoData(StreamInfo streamInfo) {
        List<StreamInfoData> list = new ArrayList<>();
        int size = streamInfo.getData().size();
        for (int i = 0; i < size; i++) {
            ArrayList arrayList = streamInfo.getData();
            LinkedHashMap map = (LinkedHashMap) arrayList.get(i);
            StreamInfoData infoData = new StreamInfoData(map);
            list.add(infoData);
        }
        return list;
    }


}



// UriComponentsBuilder : https://blog.naver.com/PostView.naver?blogId=aservmz&logNo=222322019981