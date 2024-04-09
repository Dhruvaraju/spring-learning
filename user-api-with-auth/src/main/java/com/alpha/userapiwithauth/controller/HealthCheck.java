package com.alpha.userapiwithauth.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/app")
public class HealthCheck {
    @GetMapping(value = "/health")
    public String getHealthStatus(){
        return "App Works!";
    }

    @GetMapping(value = "/token-detail")
    public Jwt getJwtDetails(@AuthenticationPrincipal Jwt jwt){
     return jwt;
    }
}
