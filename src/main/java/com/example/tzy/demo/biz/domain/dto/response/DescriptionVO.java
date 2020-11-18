package com.example.tzy.demo.biz.domain.dto.response;

import com.example.tzy.demo.database.entity.QuestionEntity;
import com.example.tzy.demo.http.config.JacksonConfig;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Tianzy
 * @create: 2020-11-18 17:10
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DescriptionVO {
    private Long id;

    /**
     * 0-未分类;1-单选;2-多选;3-判断;5-简答
     */
    private Short typeId;

    @JsonRawValue
    @JsonDeserialize(using = JacksonConfig.RawValueDeserializer.class )
    private String description;

    public static DescriptionVO fromEntity(QuestionEntity entity) {
        return DescriptionVO.builder()
                .id(entity.getId())
                .typeId(entity.getTypeId())
                .description(entity.getDescriptionJson())
                .build();
    }
}
