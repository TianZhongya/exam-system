package com.example.tzy.demo.http.controller;

import com.example.tzy.demo.biz.domain.bto.request.AuthRequest;
import com.example.tzy.demo.biz.service.AuthService;
import com.example.tzy.demo.biz.domain.bto.response.CoreUserInfo;
import com.example.tzy.demo.http.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.Duration;

import static com.example.tzy.demo.http.common.HttpConstants.SESSION_ID;

/**
 * @program: exam
 * @description: 登录验证
 * @author: Tianzy
 * @create: 2020-10-07 13:55
 **/
@Api(tags = "01-认证")
@RestController
@RequestMapping("api/v1/auth")
@Slf4j
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){this.authService=authService;}

    @ApiOperation(value="登录", notes = "登录并将session id 写入Cookie")
    @PostMapping
    public Response<String> login(@RequestBody AuthRequest authRequest,@ApiIgnore @CookieValue(value=SESSION_ID,required=false)String old_Sessionid, HttpServletResponse response){
        String sessionId = authService.createAuth(authRequest);
        try{
            authService.expireAuth(old_Sessionid);
        }catch (Exception e){
            log.error("删除sessionId失败，sessionId = "+old_Sessionid);
        }
        Cookie cookie = new Cookie("sessionId",sessionId);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(Math.toIntExact(Duration.ofDays(3).getSeconds()));
        cookie.setPath("/");
        response.addCookie(cookie);
        return Response.success(sessionId);
    }

    @GetMapping
    @ApiOperation(value="获取验证信息",notes = "登录并将session id信息存入Cookie")
    public Response<CoreUserInfo> getAuthInfo(@ApiIgnore @CookieValue(value = SESSION_ID,required = false) String sessionid){
        return Response.success(authService.getAuth(sessionid));
    }

    @DeleteMapping
    @ApiOperation(value="退出,删除凭证")
    public Response<Boolean> logout(@CookieValue(SESSION_ID) String sessionid, HttpServletRequest request,HttpServletResponse response){
        Cookie cookie = new Cookie("sessionid",null);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return Response.success(authService.expireAuth(sessionid));
    }
}
