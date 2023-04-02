package com.ewok.springbootproject.web;

import com.ewok.springbootproject.config.auth.LoginUser;
import com.ewok.springbootproject.config.auth.dto.SessionUser;
import com.ewok.springbootproject.domain.token.TokenRepository;
import com.ewok.springbootproject.service.TwitchService;
import jakarta.servlet.http.HttpSession;
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

//    private final TokenRepository tokenRepository;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
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
