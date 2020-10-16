package com.example.tzy.demo.http.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author: Tianzy
 * @create: 2020-10-15 13:29
 **/
@Data
@AllArgsConstructor
public class Response<E> {
    private int code;
    private E data;
    private String msg;

    public static <T> Response<T> success(T data){return new Response<T>(0,data,"OK");}

    public static <T> Response<T> failure(int code,String msg){return new Response<T>(code,null,msg); }

    public static <T> Response<T> failure(int code,T data,String msg){return new Response<T>(code,data,msg);}

    public static Response<?> failure(HttpStatus status){
        return new Response<>(status.value(),null,status.getReasonPhrase());
    }
}
