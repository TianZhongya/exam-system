package com.example.tzy.demo.biz.domain.dto.response;

import com.example.tzy.demo.biz.domain.dto.IdAndName;
import com.example.tzy.demo.common.util.ConvertUtils;
import com.example.tzy.demo.database.entity.CourseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: Tianzy
 * @create: 2020-11-12 16:50
 **/
@Data
@NoArgsConstructor
public class CourseDetail {

    private Long id;

    private Long subjectId;

    private String subjectName;

    private String subtitle;

    private Long creatorId;

    private String creatorName;

    private List<IdAndName<Long>> teachers;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    public static CourseDetail fromEntity(CourseEntity entity){
        CourseDetail dto = new CourseDetail();
        BeanUtils.copyProperties(entity,dto);
        List<IdAndName<Long>> teachers = ConvertUtils.extractList(
                entity.getTeachers(),
                teacher -> new IdAndName<>(teacher.getUserId(),teacher.getUsername())
        );
        dto.setTeachers(teachers);
        return dto;
    }
}
