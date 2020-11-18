package com.example.tzy.demo.biz.domain.question;

import com.example.tzy.demo.biz.common.Validations;
import com.example.tzy.demo.biz.enums.QuestionTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

/**
 * @author: Tianzy
 * @create: 2020-11-17 15:49
 **/
@Data
@Validated
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Answer {
    @ApiModelProperty(hidden = true, notes="1-单选，2-多选，3-判断，4-填空，5-简答")
    private Short typeId;

    @ApiModelProperty(hidden = true, notes = "外部有id")
    private Long questionId;

    @NotNull(groups = Validations.ValidateChoice.class)
    @ApiModelProperty(notes = "单选答案")
    private Short optionId;

    @NotNull(groups = Validations.ValidateMultiAnswerChoice.class)
    @Size(min = 1, message = "至少一个正确选项", groups = Validations.ValidateMultiAnswerChoice.class)
    @ApiModelProperty(notes = "多选答案")
    private List<@NotNull(groups = Validations.ValidateMultiAnswerChoice.class) Short> optionIds;

    @NotNull(groups = Validations.ValidateTF.class)
    @ApiModelProperty(notes = "判断答案")
    private Boolean trueOrFalse;

    @NotBlank(groups = Validations.ValidateShortAnswer.class)
    @ApiModelProperty(notes = "简答题答案")
    String text;

    public boolean check(Answer o) {
        QuestionTypeEnum type = QuestionTypeEnum.fromId(typeId);
        if(type != QuestionTypeEnum.fromId(o.typeId)) {
            throw new RuntimeException("题目类型错误");
        }
        switch (type){
            case CHOICE:
                return Objects.equals(this.optionId,o.optionId);
            case MULTIPLE_ANSWER:
                return CollectionUtils.isEqualCollection(this.optionIds,o.optionIds);
            case TRUE_O_FALSE:
                return Objects.equals(this.trueOrFalse, o.trueOrFalse);
            case SHORT_ANSWER:
                // 简答题一般不走这个逻辑
                return StringUtils.equals(this.text, o.text);
        }
        return false;
    }

    public Short getTypeId() {
        return detectType().getId();
    }

    private QuestionTypeEnum detectType() {
        if (typeId != null) {
            return QuestionTypeEnum.fromId(typeId);
        }
        if (optionId != null) {
            return QuestionTypeEnum.CHOICE;
        }
        if (optionIds != null) {
            return QuestionTypeEnum.MULTIPLE_ANSWER;
        }
        if (trueOrFalse != null) {
            return QuestionTypeEnum.TRUE_O_FALSE;
        }
        if (text != null) {
            return QuestionTypeEnum.SHORT_ANSWER;
        }
        throw new RuntimeException("答案类型丢失，且无法推断");
    }
}
