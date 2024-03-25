package ru.youmiteru.backend.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = "spring.security.enabled=true")
public class SecurityConfigTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Test
    public void testPublicEndpointAccess() throws Exception {
        mockMvc.perform(get("/public"))
            .andExpect(status().isNotFound());
    }

    @Test
    public void testProtectedEndpointAccess() throws Exception {
        mockMvc.perform(get("/protected"))
            .andExpect(status().isNotFound())
            .andExpect(unauthenticated());
    }

    @Configuration
    @EnableWebSecurity
    public static class TestSecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.authorizeRequests(authorizeRequests ->
                authorizeRequests.anyRequest().permitAll()
            ).httpBasic(withDefaults());

            return http.build();
        }
    }

    @Autowired
    public void setup(WebApplicationContext context) {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }
}
