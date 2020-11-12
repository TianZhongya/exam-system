package com.example.tzy.demo.database.mapper;

import com.example.tzy.demo.database.entity.PaperEntity;

public interface PaperMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PaperEntity record);

    int insertSelective(PaperEntity record);

    PaperEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PaperEntity record);

    int updateByPrimaryKey(PaperEntity record);
}