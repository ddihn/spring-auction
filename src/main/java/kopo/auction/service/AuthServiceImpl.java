package kopo.auction.service;

import kopo.auction.domain.User;
import kopo.auction.dto.JwtResponseDto;
import kopo.auction.dto.LoginRequestDto;
import kopo.auction.dto.SignupRequestDto;
import kopo.auction.mapper.UserMapper;
import kopo.auction.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Long signup(SignupRequestDto request) {
        if (userMapper.findByUsername(request.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }
        String role = request.getRole() == null ? "USER" : request.getRole().toUpperCase();
        if (!role.equals("USER") && !role.equals("ADMIN")) {
            throw new RuntimeException("Invalid role: " + role);
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        userMapper.insert(user);

        return user.getId();
    }

    @Override
    public JwtResponseDto login(LoginRequestDto request) {
        User user = userMapper.findByUsername(request.getUsername());
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        String accessToken = JwtUtil.generateAccessToken(user.getUsername(), user.getRole());
        String refreshToken = JwtUtil.generateRefreshToken(user.getUsername());

        return new JwtResponseDto(accessToken,
                refreshToken, user.getUsername(), user.getRole());
    }
}
