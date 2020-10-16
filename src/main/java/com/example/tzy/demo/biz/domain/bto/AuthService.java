package com.example.tzy.demo.biz.domain.bto;

import com.example.tzy.demo.biz.bean.PasswordEncoder;
import com.example.tzy.demo.biz.domain.annotation.TargetDataSource;
import com.example.tzy.demo.biz.domain.bto.response.CoreUserInfo;
import com.example.tzy.demo.biz.exception.BaseException;
import com.example.tzy.demo.biz.redis.UserAuthRedisClient;
import com.example.tzy.demo.database.entity.CoreUserEntity;
import com.example.tzy.demo.database.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.example.tzy.demo.database.commen.DatabasesConstants.EXAM_READ_ONLY;

/**
 * @author: Tianzy
 * @create: 2020-09-28 20:03
 **/
@Service
public class AuthService {
    private final UserRepository userRepository;

    private final UserAuthRedisClient authRedisClient;

    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, UserAuthRedisClient authRedisClient, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authRedisClient = authRedisClient;
        this.passwordEncoder = passwordEncoder;
    }

    @TargetDataSource(value = EXAM_READ_ONLY)
    public String createAuth(AuthRequest auth){
        CoreUserEntity userEntity = null;
        if(!StringUtils.isBlank(auth.getUsername())){
            userEntity
                    =userRepository.findByUsernameAndPasswordAndIsDel(auth.getUsername(), passwordEncoder.encoder(auth.getPassword()),(short)0);
        }else if(!StringUtils.isBlank((auth.getEmail()))){
            userEntity
                    =userRepository.findByEmailAndPasswordAndIsDel(auth.getEmail(),passwordEncoder.encoder(auth.getPassword()),(short)0);
        }else if(!StringUtils.isBlank(auth.getMobile())){
            userEntity
                    =userRepository.findByMobileAndPasswordAndIsDel(auth.getMobile(),passwordEncoder.encoder(auth.getPassword()),(short)0);
        }
        if(userEntity==null){
            throw new BaseException(HttpStatus.UNAUTHORIZED.value(),"用户名或密码错误");
        }

        CoreUserInfo coreUserInfo = CoreUserInfo.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .nickname(userEntity.getNickname())
                .roleId(userEntity.getRoleId())
                .build();
        return authRedisClient.createAuthAndGetSessionId(coreUserInfo);
    }
    public CoreUserInfo getAuth(String sessionId){
        CoreUserInfo userInfo = authRedisClient.getAuth(sessionId);
        if(userInfo==null){
            throw new BaseException(HttpStatus.UNAUTHORIZED.value(),"请登录");
        }
        return userInfo;
    }
    public boolean expireAuth(String sessionId){return authRedisClient.expireAuth(sessionId);}
}
