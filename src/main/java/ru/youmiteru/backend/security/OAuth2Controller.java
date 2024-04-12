package ru.youmiteru.backend.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.youmiteru.backend.security.discord.DiscordAPI;
import ru.youmiteru.backend.security.discord.DiscordOAuth;
import ru.youmiteru.backend.security.discord.domain.TokensResponse;
import ru.youmiteru.backend.security.discord.domain.User;

import java.io.IOException;


@Controller
@RequestMapping("api/login/oauth2/code")
public class OAuth2Controller {
    private static final Logger logger = LoggerFactory.getLogger(OAuth2Controller.class);

    /////////////////////////////START DISCORD OAUTH2////////////////////////////////////
    @Value("${spring.security.oauth2.client.registration.discord.client-id}")
    String discordClientId;

    @Value("${spring.security.oauth2.client.registration.discord.client-secret}")
    String discordClientSecret;

    @Value("${spring.security.oauth2.client.provider.discord.authorization-uri}")
    private String discordAuthorizationUri;

    @Value("${spring.security.oauth2.client.provider.discord.token-uri}")
    private String discordTokenUri;

    @Value("${spring.security.oauth2.client.provider.discord.user-info-uri}")
    private String discordUserInfoUri;

    @Value("${spring.security.oauth2.client.registration.discord.redirect-uri}")
    private String discordRedirectUri;
    @GetMapping("/discord")
    public String handleDiscordCallback(@RequestParam("code") String code) throws IOException {
        DiscordOAuth oauthHandler = new DiscordOAuth(
            discordClientId,discordClientSecret,discordRedirectUri, null);
        TokensResponse tokens = oauthHandler.getTokens(code);
        String accessToken = tokens.getAccessToken();
        String refreshToken = tokens.getRefreshToken();

        DiscordAPI api = new DiscordAPI(accessToken);
        User user = api.fetchUser();


        return "redirect:/api/user/profile";
    }

    //////////////////////////////END DISCORD OAUTH2///////////////////////////////////
}
