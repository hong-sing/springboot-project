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

//    private final TokenRepository tokenRepository;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }



//    @GetMapping("/token")
//    public String token() {
//        Token token = new Token("");
//        tokenRepository.save(token);
//        return "index";
//    }

}
