package kopo.auction.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public User(String username, String email, String password, String role) {
        this.username  = username;
        this.email     = email;
        this.password  = password;
        this.role      = role;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void changePassword(String encryptedPassword) {
        this.password  = encryptedPassword;
        this.updatedAt = LocalDateTime.now();
    }
}
