package com.example.tzy.demo.database.mapper;

import com.example.tzy.demo.database.entity.CoreUserEntity;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface CoreUserMapper {
    int insert(CoreUserEntity record);

    CoreUserEntity selectByPrimaryKey(Long id);

    Boolean softDel(Long id);

    int updateByPrimaryKeySelective(CoreUserEntity record);

    List<CoreUserEntity> selectAllByNicknameContaining(@Param("containingNickname") String containingNickname, @Param("roleId") Short roleId);
}
