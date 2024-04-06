package ru.youmiteru.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("api/login/oauth2/code")
public class OAuth2Controller {
    @Autowired
    private final OAuth2AuthorizedClientService authorizedClientService;

    @Autowired
    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService;

    @Autowired
    public OAuth2Controller(OAuth2AuthorizedClientService authorizedClientService, OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService) {
        this.authorizedClientService = authorizedClientService;
        this.oAuth2UserService = oAuth2UserService;
    }

    @GetMapping("/discord")
    public String handleDiscordCallback(@RequestParam("code") String code) {
        return "redirect:/api/user/profile";

    }
}
