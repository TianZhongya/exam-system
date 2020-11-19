package com.example.tzy.demo.biz.domain.paper;

import com.example.tzy.demo.biz.domain.dto.response.DescriptionVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: Tianzy
 * @create: 2020-11-18 20:57
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionView {
    private Short typeId;
    private Integer score;
    private List<@NotNull DescriptionVO> descriptions;
}
