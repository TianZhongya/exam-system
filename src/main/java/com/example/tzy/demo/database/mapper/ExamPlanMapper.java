package com.example.tzy.demo.database.mapper;

import com.example.tzy.demo.database.entity.ExamPlanEntity;
import com.example.tzy.demo.database.entity.ExamRecordEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamPlanMapper {
    Boolean deleteByPrimaryKey(Long id);

    int insert(ExamPlanEntity record);

    int insertSelective(ExamPlanEntity record);

    ExamPlanEntity selectByPrimaryKey(Long id);

    List<ExamPlanEntity> selectAllByCourseIdOrderByStartTimeDesc(@Param("courseId")Long courseId);

    List<ExamPlanEntity> selectAllByCourseIdAndStudentIdOrderByStartTimeDesc(
            @Param("courseId")Long courseId,
            @Param("studentId")Long studentId);

    int updateByPrimaryKeySelective(ExamPlanEntity record);

    int updateByPrimaryKey(ExamPlanEntity record);

    List<ExamPlanEntity> selectAllByStudentIdOrderByStartTimeDesc(@Param("studentId")Long studentId);

    List<ExamPlanEntity> selectAllByTeacherTeachOrderByStartTimeDesc(@Param("teacherId")Long teacherId);
}
