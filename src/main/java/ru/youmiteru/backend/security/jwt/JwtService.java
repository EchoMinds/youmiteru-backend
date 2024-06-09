package ru.youmiteru.backend.security.jwt;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import ru.youmiteru.backend.security.AuthorizationSuccessHandlerImpl;

import javax.crypto.SecretKey;

@Service
public class JwtService {
    // обрати внимание, что этот ключ генерится каждый раз при запуске приложения
    // это значит, что если мы перезапустим приложение, "старые" токены будут более невалидными
    // если нам не нужно держать долгие сессии, то можно даже оставить так и заставлять пользователей
    // перелогиниваться. Иначе придется куда-то записывать этот ключ и подгружать его при запуске приложения
    private static final SecretKey key = Jwts.SIG.HS256.key().build();


    public String generateToken(AuthorizationSuccessHandlerImpl.UserInfoFromToken token) {
        return Jwts.builder().subject(token.userEmail()).signWith(key)
            .claim("profile_picture", token.userProfilePicture())
            .claim("id", token.userId())
            // и ты ды
            .compact();
    }

    public Claims getClaims(String jwt) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt).getPayload();
    }
}
