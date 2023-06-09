package com.ewok.springbootproject.web.dto;

import com.ewok.springbootproject.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {

    private String author;
    private String meme;
    private String summary;
    private String description;
    private String reference;
    private String login;
    private String sub;

    @Builder
    public PostSaveRequestDto(String author, String meme, String summary, String description, String reference, String login, String sub) {
        this.author = author;
        this.meme = meme;
        this.summary = summary;
        this.description = description;
        this.reference = reference;
        this.login = login;
        this.sub = sub;
    }

    public Posts toEntity() {
        return Posts.builder()
                .author(author)
                .meme(meme)
                .summary(summary)
                .login(login)
                .description(description)
                .reference(reference)
                .sub(sub)
                .build();

    }
}
