package ru.youmiteru.backend.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.Base64;

@Controller
@RequestMapping("api/login/oauth2/code")
public class OAuth2Controller {
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
        // Создаем объект MultiValueMap для передачи параметров в запрос
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("code", code);
        params.add("redirect_uri", discordRedirectUri);

        // Настройка заголовков запроса
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        RestTemplate restTemplate = new RestTemplate();

        // Создаем объект HttpEntity для передачи параметров и заголовков
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

        // Определяем Basic Auth для аутентификации
        String credentials = discordClientId + ":" + discordClientSecret;
        byte[] credentialsBytes = credentials.getBytes();
        String base64Credentials = Base64.getEncoder().encodeToString(credentialsBytes);
        headers.add("Authorization", "Basic " + base64Credentials);

        // Отправляем POST запрос на обмен кода на токен доступа
        ResponseEntity<String> responseEntity = restTemplate.exchange(discordTokenUri,
            HttpMethod.POST, requestEntity, String.class);

        String accessToken = null;
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            // Создаем объект ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Парсим JSON ответ и получаем токен доступа
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readTree(responseEntity.getBody());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            accessToken = jsonNode.get("access_token").asText();
        }

        return "redirect:/api/user/profile";

    }
}
