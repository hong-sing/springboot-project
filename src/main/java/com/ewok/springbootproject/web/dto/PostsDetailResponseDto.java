package com.ewok.springbootproject.web.dto;

import com.ewok.springbootproject.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsDetailResponseDto {

    private Long id;
    private String author;
    private String meme;
    private String summary;
    private String description;
    private String login;
    private String sub;

    public PostsDetailResponseDto(Posts entity) {
        this.id = entity.getId();
        this.author = entity.getAuthor();
        this.meme = entity.getMeme();
        this.summary = entity.getSummary();
        this.description = entity.getDescription();
        this.login = entity.getLogin();
        this.sub = entity.getSub();
    }
}
