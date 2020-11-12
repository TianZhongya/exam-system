package com.example.tzy.demo.biz.domain.dto.response;

import com.example.tzy.demo.common.util.ConvertUtils;
import com.example.tzy.demo.database.entity.CourseSummaryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: Tianzy
 * @create: 2020-11-11 23:20
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseSummary {
    private Long id;

    private Long subjectId;

    private String subjectName;

    private String subtitle;

    private List<String> teacherNames;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public static CourseSummary fromEntity(CourseSummaryEntity entity){
        CourseSummary dto = new CourseSummary();
        BeanUtils.copyProperties(entity,dto);
        return dto;
    }

    public static List<CourseSummary> fromEntities(List<CourseSummaryEntity> entities){
        return ConvertUtils.extractList(entities,CourseSummary::fromEntity);
    }
}
