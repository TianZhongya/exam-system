package com.example.tzy.demo.biz.domain.dto.request;

import com.example.tzy.demo.biz.domain.dto.Paginate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author: Tianzy
 * @create: 2020-11-18 16:24
 **/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class QuestionQueryRequest extends Paginate {
    private Long subjectId;

    private Long creatorId;

    private Short typeId;

    private String keyword;
}
