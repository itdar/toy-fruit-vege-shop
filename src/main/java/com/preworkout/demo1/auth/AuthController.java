package com.preworkout.demo1.auth;

import com.preworkout.demo1.auth.dto.TokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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
    public ResponseEntity getVegeToken(HttpServletResponse response) {
        Cookie cookie = new Cookie("accessToken", ACCESS_TOKEN);
        cookie.setPath("/");
        cookie.setMaxAge(300);
        response.addCookie(cookie);

        TokenResponse tokenResponse = new TokenResponse(null);

        return ResponseEntity.ok().body(tokenResponse);
    }
}
