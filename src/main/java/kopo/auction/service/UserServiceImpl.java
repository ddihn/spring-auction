package kopo.auction.service;

import kopo.auction.domain.User;
import kopo.auction.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    // 생성자 기반 의존성 주입
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Override
    public List<User> getAllUsers() {
        return userMapper.findAll();
    }
}
