package com.example.tzy.demo.biz.domain.bto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @program: exam
 * @description: auth
 * @author: Tianzy
 * @create: 2020-09-28 20:03
 **/
@Data
@Builder
@Service
@NoArgsConstructor
@AllArgsConstructor
public class AuthService {
    long id;
    String name;
    String age;
}
