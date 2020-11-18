package com.example.tzy.demo.biz.domain.question;

import com.example.tzy.demo.biz.enums.QuestionTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author: Tianzy
 * @create: 2020-11-17 15:34
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @NotNull
    @Min(1)
    @Max(5)
    @ApiModelProperty(notes = "1-单选，2-多选，3-判断，4-填空，5-简答")
    private Short typeId;

    @NotNull
    @Valid
    private Description description;

    @NotNull
    @Valid
    private Answer answer;

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    public QuestionTypeEnum getTypeEnum(){
        return QuestionTypeEnum.fromId(typeId);
    }
}
