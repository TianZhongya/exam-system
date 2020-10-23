package com.example.tzy.demo.http.controller;

import com.example.tzy.demo.biz.service.UserManageService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Tianzy
 * @create: 2020-10-23 14:46
 **/
@Api(tags = "02-用户")
@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserManageService userManageService;

    public UserController(UserManageService userManageService) {
        this.userManageService = userManageService;
    }
}
