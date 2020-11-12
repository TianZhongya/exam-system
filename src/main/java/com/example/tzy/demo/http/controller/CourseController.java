package com.example.tzy.demo.http.controller;

import com.example.tzy.demo.biz.domain.dto.BatchGet;
import com.example.tzy.demo.biz.domain.dto.request.CourseCreateRequest;
import com.example.tzy.demo.biz.domain.dto.request.CourseQueryRequest;
import com.example.tzy.demo.biz.domain.dto.response.CoreUserInfo;
import com.example.tzy.demo.biz.domain.dto.response.CourseDetail;
import com.example.tzy.demo.biz.domain.dto.response.CourseSummary;
import com.example.tzy.demo.biz.service.CourseService;
import com.example.tzy.demo.database.entity.CourseSummaryEntity;
import com.example.tzy.demo.http.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import static com.example.tzy.demo.http.common.HttpConstants.ATTR_USER_AUTH;

/**
 * @author: Tianzy
 * @create: 2020-10-31 22:34
 **/
@Api(tags = "04-课程")
@RestController
@RequestMapping("/api/v1")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/teacher/courses")
    @ApiOperation("创建课程")
    public Response<Long> createCourse(
            @Validated @RequestBody CourseCreateRequest request,
            @ApiIgnore @RequestAttribute(ATTR_USER_AUTH) CoreUserInfo userInfo
    ){
        return Response.success(courseService.createCourse(request,userInfo));
    }

    @GetMapping("/courses")
    @ApiOperation("筛选课程")
    public Response<BatchGet<CourseSummary>> queryCourse(
            @RequestBody @Validated CourseQueryRequest request,
            @ApiIgnore @RequestAttribute(ATTR_USER_AUTH) CoreUserInfo userInfo
    ){
        return Response.success(courseService.query(request,userInfo));
    }

    @GetMapping("/course/{id}")
    @ApiOperation("id查询课程")
    public Response<CourseDetail> getMyCourse(@PathVariable Long id){
        return Response.success(courseService.getDetailById(id));
    }

    @DeleteMapping("/teacher/course")
    @ApiOperation("删除课程")
    public Response<Boolean> deleteCourse(
            @RequestParam Long id,
            @ApiIgnore @RequestAttribute(ATTR_USER_AUTH) CoreUserInfo userInfo
    ){
        return Response.success(courseService.deleteCourse(id,userInfo));
    }

    @PostMapping("/student/courses")
    @ApiOperation("选课")
    public Response<Integer> chooseCourse(
            @RequestParam Long courseId,
            @ApiIgnore @RequestAttribute CoreUserInfo userInfo
    ){
        return Response.success(courseService.chooseCourse(courseId,userInfo));
    }

    @DeleteMapping("/student/courses")
    @ApiOperation("退课")
    public Response<Integer> dropCourse(
            @RequestParam Long courseId,
            @ApiIgnore @RequestAttribute(ATTR_USER_AUTH) CoreUserInfo useInfo
    ){
        return Response.success(courseService.dropStudentCourse(courseId,useInfo));
    }
}
