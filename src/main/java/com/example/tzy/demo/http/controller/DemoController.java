package com.example.tzy.demo.http.controller;

import com.example.tzy.demo.biz.exception.BaseException;
import com.example.tzy.demo.http.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author: Tianzy
 * @create: 2020-10-31 22:36
 **/
@Api(tags = "00-测试")
@RestController
@RequestMapping("api/v1/demo")
public class DemoController {


    @ApiOperation("健康测试")
    @RequestMapping(value = "/healthCheck", method = RequestMethod.GET)
    public Response<?> healthCheck() {
        return Response.success(null);
    }

    @ApiOperation("异常测试")
    @RequestMapping(value = "/exceptionCheck", method = RequestMethod.GET)
    public Response<?> exceptionCheck() {
        throw new BaseException(0, "测试");
    }

    @ApiOperation("时间测试")
    @RequestMapping(value = "/now", method = RequestMethod.GET)
    public Response<LocalDateTime> now() {
        return Response.success(LocalDateTime.now());
    }
}
