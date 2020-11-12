package com.example.tzy.demo.database.mapper;

import com.example.tzy.demo.database.entity.RelationUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RelationUserMapper {

    int deleteByCourseId(Long id);

    RelationUserMapper selectTeacherByCourseId(Long id);

    RelationUserMapper selectStudentByCourseId(Long id);

    @Deprecated
    int batchInsertTeachers(@Param("courseId")Long courseId, @Param("list") List<RelationUserEntity> list);

    @Deprecated
    int batchInsertStudents(@Param("courseId")Long courseId, @Param("list") List<RelationUserEntity> list);

    int batchInsertTeacherByIds(@Param("courseId")Long courseId, @Param("ids") List<Long> ids);

    int insertStudentById(@Param("courseId")Long courseId, @Param("userId") Long id);

    int deleteStudentByCourseId(@Param("courseId")Long courseId, @Param("userId")Long userId);

    int deleteTeacherByCourseId(@Param("courseId")Long courseId, @Param("userId")Long userId);

    int deleteAllTeachersByCourseId(@Param("courseId")Long courseId, @Param("userId")Long userId);


}
