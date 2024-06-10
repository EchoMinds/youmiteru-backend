package ru.youmiteru.backend.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class LoginController {
    @GetMapping("/home")
    public ResponseEntity<?> home() {
        return ResponseEntity.ok("Welcome home!");
    }

    @GetMapping("/login")
    public ResponseEntity<?> login() {
        return ResponseEntity.ok("Login page");
    }
}
