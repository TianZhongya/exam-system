package com.example.tzy.demo.biz.domain.dto.request;

import com.example.tzy.demo.biz.domain.dto.response.CoreUserInfo;
import com.example.tzy.demo.common.util.JsonUtils;
import com.example.tzy.demo.database.entity.ExamRecordEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author: Tianzy
 * @create: 2020-11-20 21:26
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckRequest {
    @NotNull
    private Long examPlanId;

    @NotNull
    private Long studentId;

    private Map<Long,Integer> idToScore;

    public ExamRecordEntity toEntity(CoreUserInfo userInfo) {
        return ExamRecordEntity.builder()
                .examPlanId(examPlanId)
                .studentId(studentId)
                .releaseStatusId((byte) 4)
                .factScoreDetailJson(JsonUtils.toJson(idToScore))
                .factScore(idToScore.values().stream().mapToInt(value -> value).sum())
                .build();
    }
}
