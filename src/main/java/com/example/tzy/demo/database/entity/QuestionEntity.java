package com.example.tzy.demo.database.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "question")
@Entity
@Data
@Builder
@NoArgsConstructor
public class QuestionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "subject_id", nullable = false)
    private Long subjectId;

    @Column(name = "subject_name", nullable = false)
    private String subjectName;

    @Column(name = "creator_id", nullable = false)
    private Long creatorId;

    @Column(name = "creator_name", nullable = false)
    private String creatorName;

    /**
     * 0-未分类;1-单选;2-多选;3-判断;4:简答
     */
    @Column(name = "type_id", nullable = false)
    private Short typeId;

    @Column(name = "description_json", nullable = false)
    private String descriptionJson;

    @Column(name = "answer_json", nullable = false)
    private String answerJson;

    @Column(name = "is_del", nullable = false)
    private Boolean del;

    @Column(name = "created_time", nullable = false)
    private Timestamp createdTime;

    @Column(name = "updated_time", nullable = false)
    private Timestamp updatedTime;

}
