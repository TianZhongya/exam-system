package com.example.tzy.demo.biz.domain.dto.request;

import com.example.tzy.demo.biz.domain.dto.Paginate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author: Tianzy
 * @create: 2020-11-12 11:38
 **/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CourseQueryRequest extends Paginate {

    private Long subject_id;

    private boolean myCreated;

    private boolean myTaught;

    private boolean myChosen;
}
