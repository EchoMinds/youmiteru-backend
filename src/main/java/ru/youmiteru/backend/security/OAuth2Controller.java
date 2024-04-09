package ru.youmiteru.backend.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

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
    public String handleDiscordCallback(@RequestParam("code") String code) {
        //create body
        LinkedMultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "authorization_code");
        formData.add("code", code);
        formData.add("redirect_uri", discordRedirectUri);
        formData.add("client_id", discordClientId);
        formData.add("client_secret", discordClientSecret);

        //add sync
        HttpClient httpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .responseTimeout(Duration.ofMillis(5000))
            .doOnConnected(conn ->
                conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                    .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

        //create client
        WebClient client = WebClient.builder()
            .baseUrl(discordTokenUri)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .build();

        //create body + get response
        Mono<String> response = client.post()
            .body(BodyInserters.fromFormData(formData))
            .retrieve()
            .bodyToMono(String.class);

        // Обработка ответа от Discord API
        response
            .then(
                Mono.just("success")
                    .doOnSuccess(success -> {
                        RedirectView redirectView = new RedirectView("/api/user/profile");
                        redirectView.setExposeModelAttributes(false);
                        throw new ResponseStatusException(HttpStatus.TEMPORARY_REDIRECT, "Redirecting...", null);
                    })
                    .doOnError(error -> {
                        RedirectView redirectView = new RedirectView("api/season/homepage");
                        redirectView.setExposeModelAttributes(false);
                        throw new ResponseStatusException(HttpStatus.TEMPORARY_REDIRECT, "Redirecting...", null);
                    })
            )
            .subscribe(
                responseBody -> {
                    logger.info("Token received: {}", responseBody);
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode = null;
                    try {
                        jsonNode = objectMapper.readTree(responseBody);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    String accessToken = jsonNode.get("access_token").asText();
                },
                error -> {
                    logger.error("Error occurred while contacting Discord API", error);
                }
            );


        return "redirect:/api/user/profile";
    }

    //////////////////////////////END DISCORD OAUTH2///////////////////////////////////
}
