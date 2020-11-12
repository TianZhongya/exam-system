package com.example.tzy.demo.database.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * paper
 * @author
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaperEntity implements Serializable {
    private Long id;

    private Integer subjectId;

    private String subjectName;

    private String title;

    private Long teacherId;

    private String teacherName;

    private Integer score;

    private Integer duration;

    private String contextJson;

    private Byte isDel;

    private Date createdTime;

    private Date updatedTime;

    private static final long serialVersionUID = 1L;
}
