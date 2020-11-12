package com.example.tzy.demo.database.dao;

import com.example.tzy.demo.biz.domain.dto.Paginate;
import com.example.tzy.demo.biz.domain.dto.response.CoreUserInfo;
import com.example.tzy.demo.biz.domain.dto.response.CourseDetail;
import com.example.tzy.demo.database.entity.CourseEntity;
import com.example.tzy.demo.database.entity.CourseSummaryEntity;
import com.example.tzy.demo.database.entity.RelationUserEntity;
import com.example.tzy.demo.database.mapper.CourseMapper;
import com.example.tzy.demo.database.mapper.RelationUserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author: Tianzy
 * @create: 2020-11-09 13:42
 **/
@Repository
public class CourseDao {
    public final CourseMapper courseMapper;
    public final RelationUserMapper relationUserMapper;

    public CourseDao(CourseMapper courseMapper, RelationUserMapper relationUserMapper) {
        this.courseMapper = courseMapper;
        this.relationUserMapper = relationUserMapper;
    }

    public Long createCourse(CourseEntity entity, List<Long> teacherIds){
        courseMapper.insert(entity);
        relationUserMapper.batchInsertTeacherByIds(entity.getId(),teacherIds);
        return entity.getId();
    }

    public PageInfo<CourseSummaryEntity>  query(CourseMapper.CourseQuery query){
        PageHelper.startPage(query.getPageNum(), query.getPerPage(),"id desc");
        PageInfo<Long> page = PageInfo.of(courseMapper.queryForIds(query));
        List<Long> ids = page.getList();
        List<CourseSummaryEntity> entities;
        if (!CollectionUtils.isEmpty(ids)) {
            PageHelper.orderBy("id desc");
            entities = courseMapper.findAllByIdIn(ids);
        } else {
            entities = Collections.emptyList();
        }
        PageInfo<CourseSummaryEntity> pageInfo = new PageInfo<>(entities);
        pageInfo.setPageNum(query.getPageNum());
        pageInfo.setPageSize(query.getPerPage());
        pageInfo.setTotal(page.getTotal());
        pageInfo.setPages(page.getPages());
        return pageInfo;
    }

    public CourseEntity findCourseById(Long id){
        return courseMapper.selectDetailById(id);
    }

    public Boolean deleteCourse(Long id){
        return courseMapper.softDelete(id);
    }

    public Boolean isOwner(Long courseId, Long userId){
        return courseMapper.isOwner(courseId,userId);
    }

    public int insertStudentById(Long courseId, Long userId){
        return relationUserMapper.insertStudentById(courseId,userId);
    }

    public int deleteStudentCourseById(Long courseId, Long userId){
        return relationUserMapper.deleteStudentByCourseId(courseId,userId);
    }
}
