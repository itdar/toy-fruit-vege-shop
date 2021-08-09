package com.preworkout.demo1.auth;

import com.preworkout.demo1.auth.dto.TokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static com.preworkout.demo1.auth.domain.AccessToken.ACCESS_TOKEN;

@RestController
@Transactional
public class AuthController {

    @GetMapping(value = "/token")
    public ResponseEntity getFruitToken() {
        TokenResponse tokenResponse = new TokenResponse(ACCESS_TOKEN);
        return ResponseEntity.ok().body(tokenResponse);
    }

    @GetMapping(value = "/vegetoken")
    public ResponseEntity getVegeToken() {
        TokenResponse tokenResponse = new TokenResponse(ACCESS_TOKEN);
        return ResponseEntity.ok().body(tokenResponse);
    }
}
