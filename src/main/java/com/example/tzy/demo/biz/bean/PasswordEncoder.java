package com.example.tzy.demo.biz.bean;

public interface PasswordEncoder {
    String encoding(String rawPassword);

    default String decoding(String encodedPassword){throw new UnsupportedOperationException();}

    default boolean match(String raw,String encoded){throw new UnsupportedOperationException();}
}
