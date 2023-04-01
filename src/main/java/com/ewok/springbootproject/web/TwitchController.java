package com.ewok.springbootproject.web;

import com.ewok.springbootproject.service.PostsService;
import com.ewok.springbootproject.service.TwitchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class TwitchController {

    private final TwitchService twitchService;
    private final PostsService postsService;

    @GetMapping("/api/v1/search/{streamer}")
    public String search(Model model, @PathVariable String streamer) {
        System.out.println(streamer);
        model.addAttribute("streamer", twitchService.getStreamerInfo(streamer));
        model.addAttribute("posts", postsService.findByLoginGoodDesc(streamer));
        return "meme/post";
    }

}


/*
uri를 /api/v1 처럼 짓는 이유 : https://velog.io/@j3ss83/API-url%EC%97%90-apiv1%EB%A5%BC-%EB%B6%99%EC%9D%B4%EB%8A%94-%EC%9D%B4%EC%9C%A0
 */