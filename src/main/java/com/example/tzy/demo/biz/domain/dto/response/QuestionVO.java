package com.example.tzy.demo.biz.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonRawValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Tianzy
 * @create: 2020-11-18 15:42
 **/
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class QuestionVO {
    private Long id;

    private Long subjectId;

    private String subjectName;

    private Long creatorId;

    private String creatorName;

    /**
     * 0-未分类;1-单选;2-多选;3-判断;4-简答
     */
    private Short typeId;

    @JsonRawValue
    @ApiModelProperty(dataType = "com.example.tzy.demo.biz.domain.question.Description")
    private String description;

    @JsonRawValue
    @ApiModelProperty(dataType="com.example.tzy.demo..biz.domain.question.answer.Answer")
    private String answer;
}
