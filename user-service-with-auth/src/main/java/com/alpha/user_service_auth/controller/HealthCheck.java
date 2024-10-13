package com.alpha.user_service_auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class HealthCheck {

    @GetMapping(path = "/healthCheck")
    public ResponseEntity<String> getAppStatus(){
        return ResponseEntity.status(HttpStatus.OK).body("""
                {
                "message" : "App Working as expected"
                }
                """);
    }

    @RequestMapping(path = "/jwtDetail")
    public ResponseEntity<Jwt> getJwtDetails(@AuthenticationPrincipal Jwt jwt){
        return ResponseEntity.status(HttpStatus.OK).body(jwt);
    }
}
