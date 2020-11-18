package com.example.tzy.demo.biz.domain.dto.request;

import com.example.tzy.demo.biz.domain.question.Question;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author: Tianzy
 * @create: 2020-11-17 15:19
 **/
@Data
public class QuestionCreateRequest {
    @NotNull
    private Long subjectId;

    @ApiModelProperty(value = "回传科目名称", notes = "可选，不填则会补全")
    private String subjectName;

    @NotNull
    private Question question;
}
