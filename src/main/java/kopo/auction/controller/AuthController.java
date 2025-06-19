package kopo.auction.controller;

import kopo.auction.dto.JwtResponseDto;
import kopo.auction.dto.LoginRequestDto;
import kopo.auction.dto.SignupRequestDto;
import kopo.auction.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Long> signup(@RequestBody SignupRequestDto request) {
        Long newUserId = authService.signup(request);
        return ResponseEntity.ok(newUserId);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody LoginRequestDto request) {
        JwtResponseDto resp = authService.login(request);
        return ResponseEntity.ok(resp);
    }
}
