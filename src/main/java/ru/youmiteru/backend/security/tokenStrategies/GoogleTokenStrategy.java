package ru.youmiteru.backend.security.tokenStrategies;

import lombok.Getter;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import ru.youmiteru.backend.security.AuthorizationSuccessHandlerImpl;
import ru.youmiteru.backend.security.OAuthTokenToProfileConvertStrategy;


public class GoogleTokenStrategy implements OAuthTokenToProfileConvertStrategy {
    @Override
    public AuthorizationSuccessHandlerImpl.UserInfoFromToken extractInfoFromToken(OAuth2AuthenticationToken authenticationToken) {
        String userId = authenticationToken.getPrincipal().getAttribute("sub");
        String userEmail = authenticationToken.getPrincipal().getAttribute("email");
        String userLogin = authenticationToken.getPrincipal().getAttribute("name");
        String userAvatar = authenticationToken.getPrincipal().getAttribute("image");

        return new AuthorizationSuccessHandlerImpl.UserInfoFromToken(
            userId,
            userLogin,
            userEmail,
            userAvatar,
            "USER"
        );
    }
}
