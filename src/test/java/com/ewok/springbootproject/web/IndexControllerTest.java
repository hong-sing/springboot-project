package com.ewok.springbootproject.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void 메인페이지_로딩() {
        //when
        String body = this.restTemplate.getForObject("/", String.class);

        //then
        assertThat(body).contains("스트리머");
    }

}

/*
@ExtendWith(SpringExtension.class)
해당 테스트 클래스에서 스프링 컨텍스트를 사용할 수 있다.
스프링 컨텍스트를 사용하여 테스트를 작성하면, 실제 애플리케이션과 유사한 환경에서 테스트를 수행할 수 있기 때문에,
테스트의 신뢰성을 높일 수 있다.

@SpringBootTest(webEnvironment = RANDOM_PORT)
스프링 부트 기반의 애플리케이션을 테스트하기 위한 애노테이션
webEnvironment 속성의 값으로 RANDOM_PORT를 지정하면, 테스트용 애플리케이션의 포트 번호를 랜덤으로 지정하겠다는 것을 의미
이렇게 하면, 여러 개의 테스트를 병렬로 실행할 때 각각의 테스트가 서로 다른 포트 번호를 사용하므로,
포트 충돌 문제를 방지할 수 있다.
 */