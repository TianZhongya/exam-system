package com.example.tzy.demo.http.controller;

import com.example.tzy.demo.biz.domain.dto.PaperQueryRequest;
import com.example.tzy.demo.biz.domain.dto.request.PaperCreateRequest;
import com.example.tzy.demo.biz.domain.dto.request.PaperPreviewRequest;
import com.example.tzy.demo.biz.domain.dto.response.CoreUserInfo;
import com.example.tzy.demo.biz.domain.paper.Paper;
import com.example.tzy.demo.biz.domain.paper.PaperPreview;
import com.example.tzy.demo.biz.service.PaperService;
import com.example.tzy.demo.http.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import java.util.List;

import static com.example.tzy.demo.http.common.HttpConstants.ATTR_USER_AUTH;

/**
 * @author: Tianzy
 * @create: 2020-11-18 20:10
 **/
@Api(tags = "06-试卷")
@RestController
@RequestMapping("/api/v1")
public class PaperController {
    private final PaperService paperService;

    public PaperController(PaperService paperService) {
        this.paperService = paperService;
    }

    @PostMapping("teacher/papers")
    @ApiIgnore("创建试卷")
    public Response<Long> create(
            @RequestBody @Valid PaperCreateRequest request,
            @ApiIgnore @RequestAttribute(ATTR_USER_AUTH) CoreUserInfo userInfo
    ){
        return Response.success(paperService.create(request,userInfo));
    }

    @GetMapping("/teacher/papers/{id}")
    @ApiOperation("打开试卷")
    public Response<Paper> getOne(@PathVariable Long id){
        return Response.success(paperService.getById(id));
    }

    @PostMapping("/teacher/papers/preview")
    @ApiOperation("预览试卷")
    public Response<PaperPreview> preview(@RequestBody PaperPreviewRequest request) {
        return Response.success(paperService.preview(request));
    }

    @GetMapping("/student/plans/{planId}/paper")
    @ApiOperation(value = "学生打开次计划的试卷", notes = "学生打开就开始计时（注意id是计划的！！）")
    public Response<Paper> getAndStart(@PathVariable Long planId,
                                       @ApiIgnore @RequestAttribute(name = ATTR_USER_AUTH) CoreUserInfo userInfo) {
        return Response.success(paperService.getAndStart(planId, userInfo));
    }

    @GetMapping("/teacher/plans/{planId}/student/{studentId}/paper")
    @ApiOperation(value = "老师获取判题的试卷")
    public Response<Paper> getForJudge(@PathVariable Long planId, @PathVariable Long studentId,
                                       @ApiIgnore @RequestAttribute(name = ATTR_USER_AUTH) CoreUserInfo userInfo) {
        return Response.success(paperService.getForJudge(planId, studentId));
    }

    @GetMapping("/teacher/paper")
    @ApiOperation(value = "查询某科目下的试卷，用于创建考试（计划）")
    public Response<List<Paper>> query(PaperQueryRequest request,
                                       @ApiIgnore @RequestAttribute(name = ATTR_USER_AUTH) CoreUserInfo userInfo) {
        return Response.success(paperService.query(request, userInfo));
    }

}
