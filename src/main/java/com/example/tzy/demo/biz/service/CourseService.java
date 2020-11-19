package com.example.tzy.demo.biz.service;

import com.example.tzy.demo.biz.domain.dto.Pagination;
import com.example.tzy.demo.biz.domain.dto.BatchGet;
import com.example.tzy.demo.biz.domain.dto.request.CourseCreateRequest;
import com.example.tzy.demo.biz.domain.dto.request.CourseQueryRequest;
import com.example.tzy.demo.biz.domain.dto.response.CoreUserInfo;
import com.example.tzy.demo.biz.domain.dto.response.CourseDetail;
import com.example.tzy.demo.biz.domain.dto.response.CourseSummary;
import com.example.tzy.demo.biz.exception.BaseException;
import com.example.tzy.demo.database.annotation.TargetDataSource;
import com.example.tzy.demo.database.dao.CourseDao;
import com.example.tzy.demo.database.entity.CourseEntity;
import com.example.tzy.demo.database.entity.CourseSummaryEntity;
import com.example.tzy.demo.database.mapper.CourseMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.example.tzy.demo.database.commen.DatabasesConstants.EXAM_READ_ONLY;
import static com.example.tzy.demo.database.commen.DatabasesConstants.EXAM_READ_WRITE;

/**
 * @author: Tianzy
 * @create: 2020-11-04 20:12
 **/
@Service
public class CourseService {
    private final CourseDao courseDao;

    public CourseService(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @TargetDataSource(EXAM_READ_WRITE)
    @Transactional(rollbackFor = Exception.class)
    public Long createCourse(CourseCreateRequest request, CoreUserInfo userInfo){
        CourseEntity entity = request.toEntity(userInfo);
        Set<Long> ids = new LinkedHashSet<>(entity.getTeachers().size()+1);
        ids.add(userInfo.getId());
        ids.addAll(request.getTeacherIds());
        return courseDao.createCourse(entity,new ArrayList<>(ids));
    }

    @TargetDataSource(EXAM_READ_ONLY)
    public BatchGet<CourseSummary> query(CourseQueryRequest request, CoreUserInfo userInfo){
        CourseMapper.CourseQuery query = new CourseMapper.CourseQuery();
        BeanUtils.copyProperties(request,query);
        if(request.isMyCreated()){
            query.setCreatorId(userInfo.getId());
        }
        if(request.isMyTaught()){
            query.setTeacherId(userInfo.getId());
        }
        if(request.isMyChosen()){
            query.setStudentId(userInfo.getId());
        }
        PageInfo<CourseSummaryEntity> page = courseDao.query(query);
        return BatchGet.of(CourseSummary.fromEntities(page.getList()), Pagination.fromPage(page));
    }

    @TargetDataSource(EXAM_READ_ONLY)
    public CourseDetail getDetailById(Long id){
        return CourseDetail.fromEntity(courseDao.findCourseById(id));
    }

    @TargetDataSource(EXAM_READ_WRITE)
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteCourse(Long id, CoreUserInfo userInfo){
        if(!courseDao.isOwner(id,userInfo.getId())){
            throw new BaseException(HttpStatus.FORBIDDEN.value(),"只有课程创建者可以删除");
        }
        return courseDao.deleteCourse(id);
    }

    @TargetDataSource(EXAM_READ_WRITE)
    @Transactional(rollbackFor = Exception.class)
    public int chooseCourse(Long courseId, CoreUserInfo userInfo){
        return courseDao.insertStudentById(courseId,userInfo.getId());
    }

    @TargetDataSource(EXAM_READ_WRITE)
    @Transactional(rollbackFor = Exception.class)
    public int dropStudentCourse(Long courseId, CoreUserInfo userInfo){
        return courseDao.deleteStudentCourseById(courseId,userInfo.getId());
    }
}
