package ru.youmiteru.backend.security.jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.youmiteru.backend.domain.Role;
import ru.youmiteru.backend.security.AuthToken;
import ru.youmiteru.backend.security.Principal;

import java.io.IOException;

/**
 * !!! https://docs.spring.io/spring-security/reference/servlet/authentication/architecture.html !!!
 * Этот фильтр проверяет есть ли в хэдерах хэдер Bearer. Если есть, нам нужно проверить: это токен,
 * который выписал наш сервис, или это какая-то гадость.
 * Если это наш токен, нужно создать Authentication и запихнуть его в секьюрити контекст
 */
@Component
@RequiredArgsConstructor

public class JWTValidationFilter extends HttpFilter {


    private final JwtService jwtService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String token = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            try {
                Claims claims = jwtService.getClaims(token);
                var userEmail = claims.getSubject();
                String userId = (String) claims.get("id");
                var principal = new Principal(userEmail, userId);

                Authentication auth = new AuthToken(principal, (String) claims.get("roles"));
                SecurityContextHolder.getContext().setAuthentication(auth);
                chain.doFilter(request, response);
            } catch (Exception e) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}
