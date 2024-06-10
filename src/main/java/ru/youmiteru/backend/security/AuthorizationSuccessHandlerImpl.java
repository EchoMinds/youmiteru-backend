package ru.youmiteru.backend.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.security.jwt.JwtService;
import ru.youmiteru.backend.security.tokenStrategies.YandexTokenStrategy;

import java.io.IOException;

@Service
public class AuthorizationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    private OAuthTokenToProfileConvertStrategy yandexTokenStrategy = new YandexTokenStrategy();
    private final JwtService jwtService;

    public AuthorizationSuccessHandlerImpl(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken auth = (OAuth2AuthenticationToken) authentication;
        var userInfoFromToken = extractInfoFromToken(auth, auth.getAuthorizedClientRegistrationId());
        var generatedToken = jwtService.generateToken(userInfoFromToken);
//        System.out.printf(
//            """
//                Итак, мы сгенерировали токен \n%s\nПопробуй достучаться до /protected через POSTMAN или курлом
//                curl --location 'http://localhost:8080/protected' \
//                --header 'Authorization: Bearer %s' \
//                """,
//            generatedToken, generatedToken);
        response.addHeader("Authorization", "Bearer " + generatedToken);
    }

    private UserInfoFromToken extractInfoFromToken(OAuth2AuthenticationToken token, String clientId) {
        return switch (clientId) {
//            case "github" -> githubTokenStrategy.extractInfoFromToken(token);
            case "yandex" -> yandexTokenStrategy.extractInfoFromToken(token);
            default -> throw new Error("");
        };
    }

    public record UserInfoFromToken(
        String userId,
        String userName,
        String userEmail,
        String userProfilePicture
    ) {
    }
}
