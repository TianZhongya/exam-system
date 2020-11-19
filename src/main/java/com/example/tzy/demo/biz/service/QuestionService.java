package com.example.tzy.demo.biz.service;

import com.example.tzy.demo.biz.domain.dto.Pagination;
import com.example.tzy.demo.biz.domain.dto.BatchGet;
import com.example.tzy.demo.biz.domain.dto.request.QuestionCreateRequest;
import com.example.tzy.demo.biz.domain.dto.request.QuestionQueryRequest;
import com.example.tzy.demo.biz.domain.dto.response.CoreUserInfo;
import com.example.tzy.demo.biz.domain.dto.response.DescriptionVO;
import com.example.tzy.demo.biz.domain.dto.response.QuestionVO;
import com.example.tzy.demo.biz.exception.BaseException;
import com.example.tzy.demo.common.util.ConvertUtils;
import com.example.tzy.demo.common.util.JsonUtils;
import com.example.tzy.demo.database.annotation.TargetDataSource;
import com.example.tzy.demo.database.entity.QuestionEntity;
import com.example.tzy.demo.database.mapper.QuestionMapper;
import com.example.tzy.demo.database.repository.QuestionRepository;
import com.example.tzy.demo.database.repository.SubjectRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.tzy.demo.database.commen.DatabasesConstants.EXAM_READ_ONLY;
import static com.example.tzy.demo.database.commen.DatabasesConstants.EXAM_READ_WRITE;

/**
 * @author: Tianzy
 * @create: 2020-11-17 14:59
 **/
@Service
public class QuestionService {
    private final SubjectRepository subjectRepository;
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public QuestionService(SubjectRepository subjectRepository, QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.subjectRepository = subjectRepository;
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    @TargetDataSource(EXAM_READ_WRITE)
    @Transactional(rollbackFor = Exception.class)
    public Long createQuestion(QuestionCreateRequest request, CoreUserInfo userInfo){
        QuestionEntity entity = QuestionEntity.builder()
                .subjectId(request.getSubjectId())
                .subjectName(
                        Optional.ofNullable(request.getSubjectName())
                                .filter(cs -> !StringUtils.isBlank(cs))
                                .orElseGet(
                                        () -> subjectRepository.findById(request.getSubjectId())
                                        .orElseThrow(() -> new BaseException(HttpStatus.BAD_REQUEST.value(),"科目id不存在，id="+request.getSubjectId()))
                                        .getName()
                                )
                )
                .creatorId(userInfo.getId())
                .creatorName(userInfo.getNickname())
                .typeId(request.getQuestion().getTypeId())
                .descriptionJson(JsonUtils.toJson(request.getQuestion().getDescription()))
                .answerJson(JsonUtils.toJson(request.getQuestion().getAnswer()))
                .build();
        return questionRepository.saveAndFlush(entity).getId();
    }

    @TargetDataSource(EXAM_READ_ONLY)
    public QuestionVO findById(Long id){
        QuestionEntity entity = questionRepository.findById(id)
                .orElseThrow(() -> new BaseException(HttpStatus.BAD_REQUEST.value(), "题目问题不存在 id=" + id));
        QuestionVO vo = QuestionVO.builder()
                .description(entity.getDescriptionJson())
                .answer(entity.getAnswerJson())
                .build();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    @TargetDataSource(EXAM_READ_WRITE)
    public Integer deleteById(Long id){
        return questionMapper.deleteByPrimaryKey(id);
    }

    @TargetDataSource(EXAM_READ_ONLY)
    public BatchGet<QuestionVO> query(QuestionQueryRequest request){
        QuestionMapper.QueryCondition query = new QuestionMapper.QueryCondition();
        BeanUtils.copyProperties(request,query);

        PageHelper.startPage(request.getPageNum(),request.getPerPage(),"id desc");
        List<QuestionEntity> entities = questionMapper.query(query);
        PageInfo<QuestionEntity> page = PageInfo.of(entities);

        List<QuestionVO> voList = ConvertUtils.extractList(
                entities,
                e -> {
                    QuestionVO vo = QuestionVO.builder()
                            .description(e.getDescriptionJson())
                            .answer(e.getAnswerJson())
                            .build();
                    BeanUtils.copyProperties(e, vo);
                    return vo;
                });
        return BatchGet.of(voList, Pagination.fromPage(page));
    }

    public Map<Long, DescriptionVO> findDescription(List<Long> ids){
        List<QuestionEntity> entities = questionRepository.findAllById(ids);
        return ConvertUtils.extractMap(entities,QuestionEntity::getId,DescriptionVO::fromEntity);
    }
}
