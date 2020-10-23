package com.example.tzy.demo.http.config;

import com.example.tzy.demo.biz.enums.RoleEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * @author: Tianzy
 * @create: 2020-10-23 19:47
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "application.auth")
public class AuthConfigProperties {
    private List<String> nonLoginPrefixes;
    private Map<RoleEnum, List<String>> roleToPrefixes;
    private Map<RoleEnum, String > secret;
}
