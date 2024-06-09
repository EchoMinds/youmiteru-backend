package ru.youmiteru.backend.security;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
/**
 * Раз будет несколько oauth провайдеров, нужно будет по разному доставать из них нужную нам инфу
 */
public interface OAuthTokenToProfileConvertStrategy {
    AuthorizationSuccessHandlerImpl.UserInfoFromToken extractInfoFromToken(
        OAuth2AuthenticationToken authenticationToken);
}
