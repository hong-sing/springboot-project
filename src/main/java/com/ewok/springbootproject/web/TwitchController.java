package com.ewok.springbootproject.web;

import com.ewok.springbootproject.config.auth.LoginUser;
import com.ewok.springbootproject.config.auth.dto.SessionUser;
import com.ewok.springbootproject.service.PostsService;
import com.ewok.springbootproject.service.TwitchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class TwitchController {

    private final TwitchService twitchService;
    private final PostsService postsService;

    @GetMapping("/meme/post-save/{login}")
    public String postsSave(Model model, @PathVariable String login, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("sub", user.getSub());
        }
        model.addAttribute("login", login);
        return "meme/post-save";
    }

    @GetMapping("/meme/post/{streamer}")
    public String search(Model model, @PathVariable String streamer, @LoginUser SessionUser user, @PageableDefault(size = 15) Pageable pageable, @RequestParam(required = false, defaultValue = "0", value = "page") int pageNo) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        model.addAttribute("streamer", twitchService.getStreamerInfo(streamer));
        model.addAttribute("posts", postsService.findByLoginGoodDesc2(streamer, pageable).getBody());
        model.addAttribute("pageNo", pageNo);

        return "meme/post";
    }

    @GetMapping("/meme/post-detail/{id}")
    public String detail(Model model, @PathVariable Long id, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("sub", user.getSub());
            model.addAttribute("role", user.getRole());
        }
        model.addAttribute("post", postsService.findById(id));
        return "meme/post-detail";
    }

    @GetMapping("/meme/post-update/{id}")
    public String update(Model model, @PathVariable Long id, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        model.addAttribute("post", postsService.findById(id));
        return "meme/post-update";
    }

}


