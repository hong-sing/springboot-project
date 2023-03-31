package com.ewok.springbootproject.web;

import com.ewok.springbootproject.service.TwitchService;
import com.ewok.springbootproject.web.dto.PostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Controller
public class TwitchController {

    private final TwitchService twitchService;

    @PostMapping("/api/v1/search")
    public String search(Model model, String streamer) {
        System.out.println(streamer);
        model.addAttribute("streamer", twitchService.getStreamerInfo(streamer));
        return "meme/post";
    }

}


/*
uri를 /api/v1 처럼 짓는 이유 : https://velog.io/@j3ss83/API-url%EC%97%90-apiv1%EB%A5%BC-%EB%B6%99%EC%9D%B4%EB%8A%94-%EC%9D%B4%EC%9C%A0
 */