package com.ewok.springbootproject.web.dto;

import com.ewok.springbootproject.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostsPagingListResponseDto {

    private List<PostsListResponseDto> postsList;
    private Long totalPage;
    private Long totalCount;

    @Builder
    public PostsPagingListResponseDto(List<Posts> postsList, Long totalPage, Long totalCount) {
        this.postsList = postsList.stream().map(PostsListResponseDto::new).collect(Collectors.toList());
        this.totalPage = totalPage;
        this.totalCount = totalCount;
    }

}
