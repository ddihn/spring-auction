package kopo.auction.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class JwtUtil {
    // 최소 32바이트 이상(256비트) 길이의 비밀키
    private static final String SECRET = "ChangeThisToASufficientlyLongSecretKey123!";
    private static final SecretKey KEY = Keys.hmacShaKeyFor(
            SECRET.getBytes(StandardCharsets.UTF_8)
    );
    // 만료 기간 설정
    private static final long ACCESS_EXPIRE_MINUTES  = 15;
    private static final long REFRESH_EXPIRE_DAYS   = 7;

    /** Access Token 발급 */
    public static String generateAccessToken(String username, String role) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(username)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(Date.from(now.plus(ACCESS_EXPIRE_MINUTES, ChronoUnit.MINUTES)))
                .signWith(KEY)
                .compact();
    }
    /** Refresh Token 발급 */
    public static String generateRefreshToken(String username) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(username)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(REFRESH_EXPIRE_DAYS, ChronoUnit.DAYS)))
                .signWith(KEY)
                .compact();
    }

    /** 토큰 유효성 검사 */
    public static boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(KEY)
                    .build()
                    .parseSignedClaims(token);  // 예외가 없으면 OK
            return true;
        } catch (JwtException ex) {
            return false;
        }
    }

    /** Claims 추출 (subject, role 등) */
    public static Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
