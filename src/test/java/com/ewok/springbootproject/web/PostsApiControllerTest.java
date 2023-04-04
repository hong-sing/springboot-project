package com.ewok.springbootproject.web;

import com.ewok.springbootproject.domain.posts.Posts;
import com.ewok.springbootproject.domain.posts.PostsRepository;
import com.ewok.springbootproject.web.dto.PostSaveRequestDto;
import com.ewok.springbootproject.web.dto.PostsUpdateRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private PostsRepository postsRepository;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @AfterEach
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    void 밈_등록된다() throws Exception {
        //given
        String author = "침튜브";
        String meme = "55도발";
        String summary = "55도발 왜하냐고";
        String description = "55도발 왜하냐고는 ....";
        String reference = "http://www.youtube.com/wa...";
        String login = "zilioner";
        PostSaveRequestDto requestDto = PostSaveRequestDto.builder()
                .author(author)
                .meme(meme)
                .summary(summary)
                .description(description)
                .reference(reference)
                .login(login)
                .build();

        String url = "http://localhost:" + port + "/api/v1/save";

        //when
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(2).getMeme()).isEqualTo(meme);
        assertThat(all.get(2).getDescription()).isEqualTo(description);
        assertThat(all.get(2).getReference()).isEqualTo(reference);
    }

    @Test
    void 밈_수정된다() throws Exception {
        //given
        Posts savedPosts = postsRepository.save(Posts.builder()
                .author("침튜브")
                .meme("55도발")
                .summary("55도발 왜하냐고")
                .description("55도발 왜하냐고는 ....")
                .reference("http://www.youtube.com/wa...")
                .login("zilioner")
                .build());

        Long updateId = savedPosts.getId();
        String expectedMeme = "55도발2";
        String expectedDescription = "55도발2 왜하냐고는 ....";
        String expectedReference = "http://www.youtube.com/wa.....";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .meme(expectedMeme)
                .description(expectedDescription)
                .reference(expectedReference)
                .build();

        String url = "http://localhost:" + port + "/api/v1/update/" + updateId;

        //when
        mvc.perform(put(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        Optional<Posts> posts = postsRepository.findById(updateId);
        assertThat(posts.get().getMeme()).isEqualTo(expectedMeme);
        assertThat(posts.get().getDescription()).isEqualTo(expectedDescription);
        assertThat(posts.get().getReference()).isEqualTo(expectedReference);
    }

    @Test
    void 밈_삭제된다() throws Exception {
        //given
        Posts savedPosts = postsRepository.save(Posts.builder()
                .author("침튜브")
                .meme("55도발")
                .summary("55도발 왜하냐고")
                .description("55도발 왜하냐고는 ....")
                .reference("http://www.youtube.com/wa...")
                .login("zilioner")
                .build());

        Long deleteId = savedPosts.getId();

        String url = "http://localhost:" + port + "/api/v1/delete/" + deleteId;

        //when
        mvc.perform(delete(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //then
        Optional<Posts> posts = postsRepository.findById(deleteId);
        assertThat(posts).isEmpty();
    }
}