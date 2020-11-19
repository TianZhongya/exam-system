package com.example.tzy.demo.biz.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlInlineBinaryData;
import java.util.List;

/**
 * @author: Tianzy
 * @create: 2020-11-18 20:23
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaperCreateRequest {

    @NotNull
    private Long subjectId;

    @NotBlank
    @Size(min = 10)
    private String title;

    @Min(1)
    private Integer duration;

    @NotNull
    private List<@Valid QuestionConfig> configs;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class QuestionConfig{

        @NotNull
        private Short typeId;

        @NotNull
        private Integer score;

        @NotNull
        private List<@NotNull Long> questionIds;
    }
}
