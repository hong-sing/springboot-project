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

    @GetMapping("/post/save/{login}")
    public String postsSave(Model model, @PathVariable String login) {
        model.addAttribute("login", login);
        return "meme/post-save";
    }

    @GetMapping("/search/{streamer}")
    public String search(Model model, @PathVariable String streamer) {
        model.addAttribute("streamer", twitchService.getStreamerInfo(streamer));
        model.addAttribute("posts", postsService.findByLoginGoodDesc(streamer));
        return "meme/post";
    }

    @GetMapping("/post/update/{id}")
    public String update(Model model, @PathVariable Long id) {
        model.addAttribute("post", postsService.findById(id));
        return "meme/post-update";
    }

}


