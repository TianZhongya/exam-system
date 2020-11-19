package com.example.tzy.demo.database.mapper;

import com.example.tzy.demo.database.entity.ExamPlanEntity;

public interface ExamPlanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExamPlanEntity record);

    int insertSelective(ExamPlanEntity record);

    ExamPlanEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExamPlanEntity record);

    int updateByPrimaryKey(ExamPlanEntity record);
}