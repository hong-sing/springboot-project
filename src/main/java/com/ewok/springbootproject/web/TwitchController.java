package com.ewok.springbootproject.web;

import com.ewok.springbootproject.config.auth.LoginUser;
import com.ewok.springbootproject.config.auth.dto.SessionUser;
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
    public String postsSave(Model model, @PathVariable String login, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        model.addAttribute("login", login);
        return "meme/post-save";
    }

    @GetMapping("/search/{streamer}")
    public String search(Model model, @PathVariable String streamer, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        model.addAttribute("streamer", twitchService.getStreamerInfo(streamer));
        model.addAttribute("posts", postsService.findByLoginGoodDesc(streamer));
        return "meme/post";
    }

    @GetMapping("/post/detail/{id}")
    public String detail(Model model, @PathVariable Long id, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        model.addAttribute("post", postsService.findById(id));
        return "meme/post-detail";
    }

    @GetMapping("/post/update/{id}")
    public String update(Model model, @PathVariable Long id, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        model.addAttribute("post", postsService.findById(id));
        return "meme/post-update";
    }

}


