package kopo.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponseDto {
    private String accessToken;
    private String refreshToken;
    private String username;
    private String role;
}
