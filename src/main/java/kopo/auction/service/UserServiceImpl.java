package kopo.auction.service;

import kopo.auction.domain.User;
import kopo.auction.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.findAll();
    }

    @Override
    public String getUsernameById(Long id) {
        return userMapper.findUsernameById(id);
    }
}
