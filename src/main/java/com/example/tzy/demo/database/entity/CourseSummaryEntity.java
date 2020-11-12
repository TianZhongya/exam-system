package com.example.tzy.demo.database.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: Tianzy
 * @create: 2020-11-09 23:19
 **/
@Data
public class CourseSummaryEntity {
    private Long id;

    private Long subjectId;

    private String subjectName;

    private String subtitle;

    private String creatorName;

    private List<String> teacherNames;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
