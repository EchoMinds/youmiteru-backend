package ru.youmiteru.backend.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.RequestEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequestEntityConverter;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequestEntityConverter;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

import static ru.youmiteru.backend.security.OAuth2UserAgentUtils.withUserAgent;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/api/user/{id}").authenticated()
                .anyRequest().permitAll()
            )
            .oauth2Login((requests) ->
                requests
                    .tokenEndpoint(tokenEndpointConfig -> tokenEndpointConfig
                        .accessTokenResponseClient(accessTokenResponseClient()))
                    .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                        .userService(oAuth2UserService()))
            )

        ;

        return http.build();
    }

    @Bean
    public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient() {
        DefaultAuthorizationCodeTokenResponseClient client = new DefaultAuthorizationCodeTokenResponseClient();
        client.setRequestEntityConverter(new OAuth2AuthorizationCodeGrantRequestEntityConverter() {
            @Override
            public RequestEntity<?> convert(OAuth2AuthorizationCodeGrantRequest codeGrantRequest) {
                return withUserAgent(super.convert(codeGrantRequest));
            }
        });
        return client;
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService() {
        DefaultOAuth2UserService service = new DefaultOAuth2UserService();

        service.setRequestEntityConverter(new OAuth2UserRequestEntityConverter() {
            @Override
            public RequestEntity<?> convert(OAuth2UserRequest userRequest) {
                return withUserAgent(super.convert(userRequest));
            }
        });
        return service;
    }
}
