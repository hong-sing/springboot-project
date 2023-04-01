package com.ewok.springbootproject.web.dto;

import com.ewok.springbootproject.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {

    private String author;
    private String meme;
    private String summary;
    private String description;
    private String reference;
    private String login;

    @Builder
    public PostsUpdateRequestDto(String author, String meme, String summary, String description, String reference, String login) {
        this.author = author;
        this.meme = meme;
        this.summary = summary;
        this.description = description;
        this.reference = reference;
        this.login = login;
    }

    public Posts toEntity() {
        return Posts.builder()
                .author(author)
                .meme(meme)
                .summary(summary)
                .description(description)
                .reference(reference)
                .login(login)
                .build();
    }
}
