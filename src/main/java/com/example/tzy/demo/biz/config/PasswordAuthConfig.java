package com.example.tzy.demo.biz.config;

import com.example.tzy.demo.biz.bean.PasswordEncoder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Tianzy
 * @create: 2020-10-16 14:06
 **/
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "application.auth.password")
public class PasswordAuthConfig {
    private String salt;

    @Bean
    public PasswordEncoder passwordEncoder(){return rawPassword -> DigestUtils.sha256Hex(rawPassword+salt); }
}
