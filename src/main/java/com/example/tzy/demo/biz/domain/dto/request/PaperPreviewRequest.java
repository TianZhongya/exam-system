package com.example.tzy.demo.biz.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: Tianzy
 * @create: 2020-11-19 13:56
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaperPreviewRequest {

    @NotNull
    private Long subjectId;
    @NotNull
    private List<@Valid PreViewConfig> configs;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PreViewConfig {
        @NotNull
        private Short typeId;
        @NotNull
        private Integer score;
        @NotNull
        private Integer count;
    }
}
