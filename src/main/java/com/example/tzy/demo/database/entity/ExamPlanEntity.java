package com.example.tzy.demo.database.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * exam_plan
 * @author 
 */
@Data
public class ExamPlanEntity implements Serializable {
    private Long id;

    private Long subjectId;

    private String subjectName;

    private Long courseId;

    private Long paperId;

    private String paperTitle;

    private Integer fullScore;

    private Long teacherId;

    private String teacherName;

    private Date startTime;

    private Date endTime;

    private Date createdTime;

    private Date updatedTime;

    private Byte isDel;

    private static final long serialVersionUID = 1L;
}