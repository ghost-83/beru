package ru.ghost.beru.rest;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ghost.beru.dto.AuthRequest;
import ru.ghost.beru.service.util.JwtUtil;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    @Value("${beru.max-age}")
    private Integer maxAge;

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public void login(@RequestBody AuthRequest authRequest, HttpServletResponse response) {

        Cookie cookie = new Cookie("jwt", jwtUtil.generateToken(authRequest.username()));
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);

        response.addCookie(cookie);
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response) {

        Cookie cookie = new Cookie("jwt", "");
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);
    }
}
