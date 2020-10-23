package com.example.tzy.demo.biz.domain.bto.request;

import lombok.Data;

/**
 * @program: exam
 * @description: 登录请求
 * @author: Tianzy
 * @create: 2020-10-07 14:29
 **/
@Data
public class AuthRequest {
    private String username;
    private String email;
    private String mobile;
    private String password;
}
