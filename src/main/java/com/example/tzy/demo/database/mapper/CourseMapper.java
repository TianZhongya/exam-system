package com.example.tzy.demo.database.mapper;

import com.example.tzy.demo.database.entity.CourseEntity;
import com.example.tzy.demo.database.entity.CourseSummaryEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {
    Boolean softDelete(Long id);

    int insert(CourseEntity record);

    int insertSelective(CourseEntity record);

    CourseSummaryEntity selectSummaryById(Long id);

    Boolean isOwner(@Param("courseId")Long courseId,@Param("userId")Long userId);

    CourseEntity selectDetailById(Long id);

    List<Long> queryForIds(@Param("query")CourseQuery query);

    List<CourseSummaryEntity> findAllByIdIn(@Param("ids")List<Long> ids);

    int updateByPrimaryKeySelective(CourseEntity record);

    int updateByPrimaryKey(CourseEntity record);


    @Data
    @NoArgsConstructor
    class CourseQuery{
        private Long subjectId;
        private Long creatorId;
        private Long teacherId;
        private Long studentId;
        private int perPage;
        private int pageNum;
    }
}
