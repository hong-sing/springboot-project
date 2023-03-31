package com.ewok.springbootproject.web;

import com.ewok.springbootproject.domain.token.TokenRepository;
import com.ewok.springbootproject.service.TwitchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/post/save/{login}")
    public String postsSave(Model model, @PathVariable String login) {
        System.out.println(login);
        model.addAttribute("login", login);
        return "meme/post-save";
    }

//    @GetMapping("/token")
//    public String token() {
//        Token token = new Token("");
//        tokenRepository.save(token);
//        return "index";
//    }

}
