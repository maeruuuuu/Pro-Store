package com.example.universitas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class TestController {

    @GetMapping
    public ResponseEntity<String> hello(Authentication authentication) {
        final String body = "hello" + authentication.getName();
        return ResponseEntity.ok(body);
    }
}
