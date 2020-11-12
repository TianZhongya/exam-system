package com.example.tzy.demo.database.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;

/**
 * course
 * @author
 */
@Data
public class CourseEntity implements Serializable {
    private Long id;

    private Long subjectId;

    private String subjectName;

    private String subtitle;

    private Long creatorId;

    private String creatorName;

    @Transient
    private List<RelationUserEntity> teachers;

    private Date startTime;

    private Date endTime;

    private Integer isDel;

    private Date createdTime;

    private Date updatedTime;

    private static final long serialVersionUID = 1L;

}
