package com.example.tzy.demo.database.mapper;

import com.example.tzy.demo.database.entity.PaperEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaperMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PaperEntity record);

    int insertSelective(PaperEntity record);

    PaperEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PaperEntity record);

    int updateByPrimaryKey(PaperEntity record);

    List<PaperEntity> query(@Param("condition")QueryCondition condition);

    @NoArgsConstructor
    @Data
    @Builder
    @AllArgsConstructor
    class QueryCondition{

        private Long subjectId;

        private Long teacherId;

        private String keyword;
    }
}
