package com.example.tzy.demo.database.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

/**
 * exam_record
 * @author
 */
@Data
@Builder
public class ExamRecordEntity implements Serializable {
    private Long id;

    private Long examPlanId;

    private Long paperId;

    private Long studentId;

    private String studentName;

    private Integer factScore;

    private String contentJson;

    /**
     * '0-未答卷，1-已开考，开始计时，2-已交卷，等待判卷，4-完成;
     */
    private Byte releaseStatusId;

    private String factScoreDetailJson;

    private Byte isDel;

    private Date createdTime;

    private Date updatedTime;

    private static final long serialVersionUID = 1L;
}
