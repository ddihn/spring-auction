package kopo.auction.service;

import kopo.auction.dto.JwtResponseDto;
import kopo.auction.dto.LoginRequestDto;
import kopo.auction.dto.SignupRequestDto;

public interface AuthService {
    Long signup(SignupRequestDto request);
    JwtResponseDto login(LoginRequestDto request);
}
