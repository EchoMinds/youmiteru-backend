package ru.youmiteru.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api")
public class OAuth2LoginController {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @Autowired
    private OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService;

    @GetMapping("/login/oauth2/code/discord")
    public String handleOAuth2Redirect(@RequestParam String code) {
        // Перенаправление пользователя
        return "redirect:/api/season/homepage";
    }
}
