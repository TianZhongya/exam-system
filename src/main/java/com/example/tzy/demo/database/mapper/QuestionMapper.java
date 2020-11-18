package com.example.tzy.demo.database.mapper;

import com.example.tzy.demo.database.entity.QuestionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: Tianzy
 * @create: 2020-11-18 14:48
 **/
@Mapper
public interface QuestionMapper {
    int deleteByPrimaryKey(Long id);

    List<QuestionEntity> query(@Param("condition")QueryCondition condition);

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class QueryCondition{
        private Long subjectId;
        private Long creatorId;
        private Short typeId;
        private String keyword;
        private Long limit;
        private Long offset;
    }
}
