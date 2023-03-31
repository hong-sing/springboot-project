package com.ewok.springbootproject.web;

import com.ewok.springbootproject.service.PostsService;
import com.ewok.springbootproject.web.dto.PostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/save")
    public String save(@RequestBody PostSaveRequestDto requestDto) {
        postsService.save(requestDto);
        return "a";
    }
}
