package com.example.tzy.demo.biz.service;

import com.example.tzy.demo.biz.bean.PasswordEncoder;
import com.example.tzy.demo.biz.domain.bto.request.UserRegisterRequest;
import com.example.tzy.demo.biz.enums.RoleEnum;
import com.example.tzy.demo.biz.exception.BaseException;
import com.example.tzy.demo.database.annotation.TargetDataSource;
import com.example.tzy.demo.database.entity.CoreUserEntity;
import com.example.tzy.demo.database.entity.UserValidationEntity;
import com.example.tzy.demo.database.mapper.CoreUserMapper;
import com.example.tzy.demo.database.repository.UserRepository;
import com.example.tzy.demo.database.repository.UserValidateRepository;
import com.example.tzy.demo.http.config.AuthConfigProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.DataBindingException;
import java.util.Map;

import static com.example.tzy.demo.database.commen.DatabasesConstants.EXAM_READ_WRITE;

/**
 * @author: Tianzy
 * @create: 2020-10-23 15:00
 **/
@Service
public class UserManageService {
    private final Map<RoleEnum,String> roleAndSecret;

    private final UserRepository userRepository;

    private final UserValidateRepository validateRepository;

    private final PasswordEncoder passwordEncoder;

    private final CoreUserMapper coreUserMapper;

    public UserManageService(AuthConfigProperties properties, UserRepository userRepository, UserValidateRepository validateRepository, PasswordEncoder passwordEncoder, CoreUserMapper coreUserMapper) {
        roleAndSecret = properties.getSecret();
        this.userRepository = userRepository;
        this.validateRepository = validateRepository;
        this.passwordEncoder = passwordEncoder;
        this.coreUserMapper = coreUserMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    @TargetDataSource(EXAM_READ_WRITE)
    public Long registerUser(UserRegisterRequest request){
        RoleEnum roleEnum = RoleEnum.fromId(request.getRoleId());
        String secret = roleAndSecret.get(roleEnum);
        if(!StringUtils.equals(request.getSecret(),secret)){
            throw new BaseException(HttpStatus.BAD_REQUEST.value(),"邀请码错误，请联系运维");
        }

        UserValidationEntity validation = validateRepository.checkUserInfoDup(request.getUsername(),request.getEmail(),request.getMobile());

        if(!validation.validated()){
            String field;
            if(validation.isExistUsername()){
                field="用户名";
            }else if(validation.isExistEmail()){
                field="邮箱";
            }else {
                field="手机号";
            }
            throw new BaseException(HttpStatus.BAD_REQUEST.value(),field+"已被使用");
        }
        CoreUserEntity userEntity =
                CoreUserEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encoding(request.getPassword()))
                .email(request.getEmail())
                .mobile(request.getMobile())
                .roleId(request.getRoleId())
                .nickname(request.getNickname())
                .build();
        try{
            return userRepository.save(userEntity).getId();
        }catch (DataIntegrityViolationException e){
            throw new BaseException(HttpStatus.BAD_REQUEST.value(),"用户名、邮箱、手机号等可能有重复",e);
        }
    }
}
