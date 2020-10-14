package com.example.tzy.demo.biz.domain.bto.response;

import com.example.tzy.demo.database.entity.CoreUserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Tianzy
 * @create: 2020-10-14 22:51
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoreUserInfo {
    private Long id;
    private String username;
    private String nickname;
    private Short roleId;

    public static CoreUserInfo from(CoreUserEntity entity){
        return CoreUserInfo.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .nickname(entity.getNickname())
                .roleId(entity.getRoleId())
                .build();
    }
}
