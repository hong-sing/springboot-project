package com.ewok.springbootproject.web;

import com.ewok.springbootproject.domain.token.TokenRepository;
import com.ewok.springbootproject.service.TwitchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final TokenRepository tokenRepository;
    private final TwitchService twitchService;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @PostMapping("/search")
    public String search(Model model, String streamer) {
        System.out.println(streamer);
        model.addAttribute("streamer", twitchService.getStreamerInfo(streamer));
        return "meme/post";
    }

//    @GetMapping("/token")
//    public String token() {
//        Token token = new Token("");
//        tokenRepository.save(token);
//        return "index";
//    }

}
