package com.example.tzy.demo.http.controller;

import com.example.tzy.demo.biz.domain.bto.BatchGet;
import com.example.tzy.demo.biz.domain.bto.request.UserQueryRequest;
import com.example.tzy.demo.biz.domain.bto.request.UserRegisterRequest;
import com.example.tzy.demo.biz.domain.bto.response.CoreUserInfo;
import com.example.tzy.demo.biz.service.UserManageService;
import com.example.tzy.demo.http.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value="注册用户",notes="需要邀请码")
    @PostMapping
    public Response<Long> registerUser(@RequestBody @Validated UserRegisterRequest registerRequest){
        return Response.success(userManageService.registerUser(registerRequest));
    }

    @ApiOperation(value="查找用户", notes = "支持模糊搜索")
    public Response<BatchGet<CoreUserInfo>> find(UserQueryRequest request){
        return Response.success(userManageService.query(request));
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/{id}")
    public Response<Boolean> delete(@PathVariable Long id){
        return Response.success(userManageService.delete(id));
    }
}
