package ru.youmiteru.backend.security.discord;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.hc.core5.net.URIBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import ru.youmiteru.backend.security.discord.domain.TokensResponse;

import java.io.IOException;
import java.net.URISyntaxException;

public class DiscordOAuth {
    private static final Gson gson = (new GsonBuilder()).serializeNulls().enableComplexMapKeySerialization().create();
    private static final String GRANT_TYPE_AUTHORIZATION = "authorization_code";
    private static final String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";
    private final String clientID;
    private final String clientSecret;
    private final String redirectUri;
    private final String[] scope;

    private static TokensResponse toObject(String str) {
        return (TokensResponse) gson.fromJson(str, TokensResponse.class);
    }

    public String getAuthorizationURL() {
        return this.getAuthorizationURL((String) null);
    }

    public String getAuthorizationURL(String state) {
        URIBuilder builder;
        try {
            builder = new URIBuilder("https://discord.com/api/oauth2/authorize");
        } catch (URISyntaxException var4) {
            return null;
        }

        builder.addParameter("response_type", "code");
        builder.addParameter("client_id", this.clientID);
        builder.addParameter("redirect_uri", this.redirectUri);
        if (state != null && !state.isEmpty()) {
            builder.addParameter("state", state);
        }

        return builder + "&scope=" + String.join("%20", this.scope);
    }

    public TokensResponse getTokens(String code) throws IOException {
        Connection request = Jsoup.connect("https://discord.com/api/oauth2/token")
            .data("client_id", this.clientID).data("client_secret", this.clientSecret)
            .data("grant_type", "authorization_code").data("code", code)
            .data("redirect_uri", this.redirectUri).data("scope", String.join(" ", this.scope))
            .ignoreContentType(true);
        String response = request.post().body().text();
        return toObject(response);
    }

    public TokensResponse refreshTokens(String refreshToken) throws IOException {
        Connection request = Jsoup.connect("https://discord.com/api/oauth2/token")
            .data("client_id", this.clientID).data("client_secret", this.clientSecret)
            .data("grant_type", "refresh_token").data("refresh_token", refreshToken)
            .ignoreContentType(true);
        String response = request.post().body().text();
        return toObject(response);
    }

    public DiscordOAuth(String clientID, String clientSecret, String redirectUri, String[] scope) {
        this.clientID = clientID;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.scope = scope;
    }
}
