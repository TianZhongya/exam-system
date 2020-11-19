package com.example.tzy.demo.database.mapper;

import com.example.tzy.demo.database.entity.ExamRecordEntity;
import org.apache.ibatis.annotations.Param;

public interface ExamRecordMapper {
    int insertIfNotExist(ExamRecordEntity record);

    int deleteByPrimaryKey(Long id);

    int insert(ExamRecordEntity record);

    int insertSelective(ExamRecordEntity record);

    ExamRecordEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExamRecordEntity record);

    int updateByPrimaryKey(ExamRecordEntity record);

    ExamRecordEntity selectByExamPlanIdAndStudentId(@Param("examPlanId")Long examPlanId, @Param("studentId")Long studentId);
}
