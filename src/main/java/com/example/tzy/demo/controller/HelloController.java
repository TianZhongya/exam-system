package com.example.tzy.demo.controller;

import com.example.tzy.demo.biz.domain.bto.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: exam
 * @description: test
 * @author: Tianzy
 * @create: 2020-09-27 20:01
 **/
@RestController
@RequestMapping("/api/v1")
public class HelloController {
    @PostMapping("/hello")
    public String hello(String name){
        AuthService service = AuthService.builder()
                .age("15")
                .name(name)
                .id(145)
                .build();
        return service.toString();
    }
    @GetMapping("/get/{id}")
    public String getTest(@PathVariable Long id){
        AuthService service = AuthService.builder()
                .age("15")
                .name("tzy")
                .id(id)
                .build();
        return service.toString();
    }
}
