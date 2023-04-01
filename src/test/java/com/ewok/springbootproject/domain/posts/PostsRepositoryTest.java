package com.ewok.springbootproject.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @Test
    public void 게시글저장_불러오기() {
        //given
        String summary = "summary";
        String login = "login";
        String meme = "meme";
        String author = "author";
        String description = "description";
        String reference = "reference";

        postsRepository.save(Posts.builder()
                .summary("summary")
                .login("login")
                .meme("meme")
                .author("author")
                .description("description")
                .reference("reference")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getMeme()).isEqualTo(meme);
        assertThat(posts.getAuthor()).isEqualTo(author);
        assertThat(posts.getDescription()).isEqualTo(description);
        assertThat(posts.getReference()).isEqualTo(reference);
        assertThat(posts.getSummary()).isEqualTo(summary);
        assertThat(posts.getLogin()).isEqualTo(login);
    }

    @Test
    void 게시글_좋아요_내림차순으로_불러오기() {
        //given
        String summary = "summary";
        String login = "nanajam777";
        String meme = "meme";
        String author = "author";
        String description = "description";
        String reference = "reference";
        int good = 100;

        postsRepository.save(Posts.builder()
                .summary("summary")
                .login("nanajam777")
                .meme("meme")
                .author("author")
                .description("description")
                .reference("reference")
                .good(100)
                .build());

        postsRepository.save(Posts.builder()
                .summary("summary2")
                .login("nanajam777")
                .meme("meme2")
                .author("author2")
                .description("description2")
                .reference("reference2")
                .good(50)
                .build());

        //when
        List<Posts> postsList = postsRepository.findByLoginGoodDesc(login);

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getMeme()).isEqualTo(meme);
        assertThat(posts.getAuthor()).isEqualTo(author);
        assertThat(posts.getDescription()).isEqualTo(description);
        assertThat(posts.getReference()).isEqualTo(reference);
        assertThat(posts.getSummary()).isEqualTo(summary);
        assertThat(posts.getLogin()).isEqualTo(login);
        assertThat(posts.getGood()).isEqualTo(good);
    }

    @Test
    void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2023, 3, 30, 0, 0, 0);
        postsRepository.save(Posts.builder()
                        .summary("summary")
                        .login("login")
                        .meme("meme")
                        .author("author")
                        .description("description")
                        .reference("reference")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        System.out.println(posts.getId());

        System.out.println(">>>>>>>>> createDate="+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
