package ru.youmiteru.backend.security;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {



    @GetMapping
    public ResponseEntity<?> login() {
        return ResponseEntity.ok("Login page");
    }
}
