package com.example.tzy.demo.biz.domain.paper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: Tianzy
 * @create: 2020-11-19 13:55
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaperPreview {

    private Integer score;

    private List<QuestionView> questions;

}
