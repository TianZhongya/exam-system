package com.example.tzy.demo.biz.exception;

import lombok.Getter;

/**
 * @author: Tianzy
 * @create: 2020-10-16 14:58
 **/
public class BaseException extends RuntimeException{
    @Getter
    private final int code;

    public BaseException(int code, String message){
        super(message);
        this.code=code;
    }

    public BaseException(int code,String message,Throwable cause){
        super(message,cause);
        this.code=code;
    }
}
