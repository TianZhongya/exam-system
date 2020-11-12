package com.example.tzy.demo.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: Tianzy
 * @create: 2020-11-09 13:53
 **/
@Data
@EqualsAndHashCode(of={"courseId","userId"})
public class RelationUserEntity {
    private Long id;

    private Long courseId;

    private Long userId;

    private String username;
}
