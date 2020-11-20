package com.example.tzy.demo.biz.domain.dto.request;

import com.example.tzy.demo.biz.domain.dto.response.CoreUserInfo;
import com.example.tzy.demo.biz.domain.question.Answer;
import com.example.tzy.demo.common.util.JsonUtils;
import com.example.tzy.demo.database.entity.ExamRecordEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author: Tianzy
 * @create: 2020-11-20 21:23
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DraftUpdateRequest {

    @NotNull
    private Long examPlanId;

    @NotNull
    private Map<Long, @NotNull @Valid Answer> idToAnswer;

    public ExamRecordEntity toEntity(CoreUserInfo userInfo) {
        return ExamRecordEntity.builder()
                .examPlanId(examPlanId)
                .releaseStatusId((byte) 1)
                .studentId(userInfo.getId())
                .studentName(userInfo.getNickname())
                .contentJson(JsonUtils.toJson(idToAnswer))
                .build();
    }
}

