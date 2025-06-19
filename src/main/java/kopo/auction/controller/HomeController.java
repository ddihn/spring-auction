package kopo.auction.controller;

import kopo.auction.domain.User;
import kopo.auction.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {
    private final BCryptPasswordEncoder passwordEncoder;

    private final UserService userService;

    public HomeController(BCryptPasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "OK";
    }

    @GetMapping("/test/bcrypt")
    public String encode(@RequestParam String pw) {
        return this.passwordEncoder.encode(pw);
    }


    @GetMapping("/test/api/users")
    public List<User> listUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/test/username/{id}")
    public String getUsername(@PathVariable Long id) {
        return userService.getUsernameById(id);
    }
}
