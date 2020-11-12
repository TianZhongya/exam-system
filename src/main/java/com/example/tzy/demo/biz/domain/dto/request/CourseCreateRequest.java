package com.example.tzy.demo.biz.domain.dto.request;

import com.example.tzy.demo.biz.domain.dto.response.CoreUserInfo;
import com.example.tzy.demo.database.entity.CourseEntity;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: Tianzy
 * @create: 2020-11-11 13:31
 **/
@Data
@Builder
@AllArgsConstructor
public class CourseCreateRequest {
    @NotNull
    private Long subjectId;

    @NotBlank
    private String subTitle;

    @NotEmpty
    private List<@NotNull @Valid Long> teacherIds;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private LocalDateTime endTime;

    public CourseEntity toEntity(CoreUserInfo coreUserInfo){
        CourseEntity courseEntity = new CourseEntity();
        BeanUtils.copyProperties(this,courseEntity);
        courseEntity.setCreatorId(coreUserInfo.getId());
        courseEntity.setCreatorName(coreUserInfo.getNickname());
        return courseEntity;
    }
}
