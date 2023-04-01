package com.ewok.springbootproject.web.dto;

import com.ewok.springbootproject.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {

    private Long id;
    private String meme;
    private String summary;
    private String reference;
    private String author;
    private int good;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.meme = entity.getMeme();
        this.summary = entity.getSummary();
        this.reference = entity.getReference();
        this.author = entity.getAuthor();
        this.good = entity.getGood();
        this.modifiedDate = entity.getModifiedDate();
    }
}
