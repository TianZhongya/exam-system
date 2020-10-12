package com.example.tzy.demo.http.controller;

import com.example.tzy.demo.biz.domain.bto.AuthRequest;
import com.example.tzy.demo.biz.domain.bto.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

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
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){this.authService=authService;}

    @ApiOperation(value="登录", notes = "登录并将session id 写入Cookie")
    @PostMapping
    public Response<String> login(@RequestBody AuthRequest authRequest,@ApiIgnore @CookieValue(value=SESSION_ID,required=false)String old_Sessionid, HttpServletResponse reponse){
    }
}
