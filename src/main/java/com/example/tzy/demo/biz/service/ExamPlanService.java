package com.example.tzy.demo.biz.service;

import com.example.tzy.demo.biz.domain.dto.request.ExamPlanCreateRequest;
import com.example.tzy.demo.biz.domain.dto.response.CoreUserInfo;
import com.example.tzy.demo.biz.domain.dto.response.ExamPlan;
import com.example.tzy.demo.biz.domain.dto.response.ExamPlanDetail;
import com.example.tzy.demo.common.util.ConvertUtils;
import com.example.tzy.demo.database.annotation.TargetDataSource;
import com.example.tzy.demo.database.entity.ExamPlanEntity;
import com.example.tzy.demo.database.mapper.ExamPlanMapper;
import com.example.tzy.demo.database.mapper.ExamRecordMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.tzy.demo.database.commen.DatabasesConstants.EXAM_READ_ONLY;
import static com.example.tzy.demo.database.commen.DatabasesConstants.EXAM_READ_WRITE;

/**
 * @author: Tianzy
 * @create: 2020-11-19 18:09
 **/
@Service
public class ExamPlanService {
    private final ExamPlanMapper examPlanMapper;

    private final ExamRecordMapper examRecordMapper;

    public ExamPlanService(ExamPlanMapper examPlanMapper, ExamRecordMapper examRecordMapper) {
        this.examPlanMapper = examPlanMapper;
        this.examRecordMapper = examRecordMapper;
    }

    @TargetDataSource(EXAM_READ_WRITE)
    @Transactional(rollbackFor = Exception.class)
    public Long create(ExamPlanCreateRequest request, CoreUserInfo userInfo) {
        ExamPlanEntity entity = new ExamPlanEntity();
        BeanUtils.copyProperties(request, entity);
        entity.setTeacherId(userInfo.getId());
        examPlanMapper.insert(entity);
        return entity.getId();
    }

    @TargetDataSource(EXAM_READ_ONLY)
    public ExamPlanDetail selectByPrimaryKey(Long id) {
        ExamPlanEntity entity = examPlanMapper.selectByPrimaryKey(id);
        return ExamPlanDetail.fromEntity(entity);
    }

    @TargetDataSource(EXAM_READ_WRITE)
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteById(Long id, CoreUserInfo userInfo) {
        //TODO 鉴权
        return examPlanMapper.deleteByPrimaryKey(id);
    }

    @TargetDataSource(EXAM_READ_ONLY)
    public List<ExamPlan> findByStudentId(Long id) {
        return ConvertUtils.extractList(
                examPlanMapper.selectAllByStudentIdOrderByStartTimeDesc(id),
                ExamPlan::fromEntity);
    }

    @TargetDataSource(EXAM_READ_ONLY)
    public List<ExamPlan> findByTeacherId(Long id) {
        return ConvertUtils.extractList(
                examPlanMapper.selectAllByTeacherTeachOrderByStartTimeDesc(id),
                ExamPlan::fromEntity);
    }

    @TargetDataSource(EXAM_READ_ONLY)
    public List<ExamPlan.SimpleExamRecord> findRecordByTeacherId(Long id) {
        return ConvertUtils.extractList(
                examRecordMapper.selectByTeacherId(id),
                ExamPlan.SimpleExamRecord::fromEntity);
    }

    @TargetDataSource(EXAM_READ_ONLY)
    public List<ExamPlan> findByCourseId(Long id) {
        return ConvertUtils.extractList(
                examPlanMapper.selectAllByCourseIdOrderByStartTimeDesc(id),
                ExamPlan::fromEntity);
    }

    @TargetDataSource(EXAM_READ_ONLY)
    public List<ExamPlan> findByCourseId(Long courseId, Long studentId) {
        return ConvertUtils.extractList(
                examPlanMapper.selectAllByCourseIdAndStudentIdOrderByStartTimeDesc(courseId, studentId),
                ExamPlan::fromEntity);
    }
}
