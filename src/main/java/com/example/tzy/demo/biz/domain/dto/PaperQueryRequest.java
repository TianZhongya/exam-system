package com.example.tzy.demo.biz.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Tianzy
 * @create: 2020-11-19 14:55
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperQueryRequest {

    private Long subjectId;

    private boolean myCreated;

    private String keyword;
}
