package ru.youmiteru.backend.security.tokenStrategies;


import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import ru.youmiteru.backend.security.AuthorizationSuccessHandlerImpl;
import ru.youmiteru.backend.security.OAuthTokenToProfileConvertStrategy;

public class YandexTokenStrategy implements OAuthTokenToProfileConvertStrategy {
    @Override
    public AuthorizationSuccessHandlerImpl.UserInfoFromToken extractInfoFromToken(OAuth2AuthenticationToken authenticationToken) {
        String userId = authenticationToken.getPrincipal().getAttribute("id");
        String userLogin = authenticationToken.getPrincipal().getAttribute("login");
        String userEmail = authenticationToken.getPrincipal().getAttribute("default_email");
        Boolean isAvatarEmpty = authenticationToken.getPrincipal().getAttribute("is_avatar_empty");
        String userProfilePicture = null;
        if (Boolean.FALSE.equals(isAvatarEmpty)) {
            userProfilePicture = buildAvatarUrl(authenticationToken.getPrincipal().getAttribute("default_avatar_id"));
        }
        return new AuthorizationSuccessHandlerImpl.UserInfoFromToken(
            userId,
            userLogin,
            userEmail,
            userProfilePicture,
            "USER"
        );
    }

    private String buildAvatarUrl(String avatarId) {
        return String.format("https://avatars.yandex.net/get-yapic/%s/islands-200", avatarId);
    }
}
