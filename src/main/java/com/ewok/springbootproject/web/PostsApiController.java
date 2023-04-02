package com.ewok.springbootproject.web;

import com.ewok.springbootproject.config.auth.LoginUser;
import com.ewok.springbootproject.config.auth.dto.SessionUser;
import com.ewok.springbootproject.service.PostsService;
import com.ewok.springbootproject.web.dto.PostSaveRequestDto;
import com.ewok.springbootproject.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/save")
    public Long save(Model model, @RequestBody PostSaveRequestDto requestDto, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/update/{id}")
    public Long update(Model model, @PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/delete/{id}")
    public Long delete(Model model, @PathVariable Long id, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        postsService.delete(id);
        return id;
    }
}


/*
uri를 /api/v1 처럼 짓는 이유 : https://velog.io/@j3ss83/API-url%EC%97%90-apiv1%EB%A5%BC-%EB%B6%99%EC%9D%B4%EB%8A%94-%EC%9D%B4%EC%9C%A0
 */