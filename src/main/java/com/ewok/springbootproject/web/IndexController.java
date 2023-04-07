package com.ewok.springbootproject.web;

import com.ewok.springbootproject.config.auth.LoginUser;
import com.ewok.springbootproject.config.auth.dto.SessionUser;
import com.ewok.springbootproject.service.TwitchService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class IndexController {

//    private final TokenRepository tokenRepository;
    private final HttpSession httpSession;
    private final TwitchService twitchService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {

        if (user != null) {
            model.addAttribute("user", user);
        }
        model.addAttribute("streamInfoData", twitchService.getStreamInfo("ko"));
        return "index";
    }

    @GetMapping("/loginForm")
    public String login() {
        return "member/login-form";
    }



//    @GetMapping("/token")
//    public String token() {
//        Token token = new Token("");
//        tokenRepository.save(token);
//        return "index";
//    }

}
