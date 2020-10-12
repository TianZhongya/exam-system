package com.example.tzy.demo.biz.domain.bto;

import com.example.tzy.demo.biz.redis.UserAuthRedisClient;
import com.example.tzy.demo.database.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * @author: Tianzy
 * @create: 2020-09-28 20:03
 **/
@Service
public class AuthService {
    private final UserRepository userRepository;

    private final UserAuthRedisClient authRedisClient;

    public AuthService(UserRepository userRepository, UserAuthRedisClient authRedisClient) {
        this.userRepository = userRepository;
        this.authRedisClient = authRedisClient;
    }
}
