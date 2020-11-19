package com.example.tzy.demo.biz.domain.paper;

import com.example.tzy.demo.biz.domain.dto.response.ExamRecord;
import com.example.tzy.demo.common.util.ConvertUtils;
import com.example.tzy.demo.database.entity.PaperEntity;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;

/**
 * @author: Tianzy
 * @create: 2020-11-19 13:27
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paper {

    private Long id;

    private Long subjectId;

    private String subjectName;

    private String title;

    private Long teacherId;

    private String teacherName;

    private Integer score;

    private Integer duration;

    @JsonRawValue
    private String questions;

    @Nullable
    private ExamRecord examRecord;

    public static Paper fromEntity(PaperEntity entity) {
        Paper paper = new Paper();
        BeanUtils.copyProperties(entity, paper);
        paper.setQuestions(entity.getContextJson());
        ExamRecord examRecord = ExamRecord.from(entity.getExamRecordEntity());
        paper.setExamRecord(examRecord);
        return paper;
    }

    public static List<Paper> fromEntity(Collection<PaperEntity> entities) {
        return ConvertUtils.extractList(entities, Paper::fromEntity);
    }

}
