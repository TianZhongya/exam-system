package com.example.tzy.demo.http.controller;

import com.example.tzy.demo.biz.domain.dto.Pagination;
import com.example.tzy.demo.biz.domain.dto.BatchGet;
import com.example.tzy.demo.biz.domain.dto.IdAndName;
import com.example.tzy.demo.biz.domain.dto.response.CoreUserInfo;
import com.example.tzy.demo.database.annotation.TargetDataSource;
import com.example.tzy.demo.database.entity.SubjectEntity;
import com.example.tzy.demo.database.repository.SubjectRepository;
import com.example.tzy.demo.http.common.Response;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.tzy.demo.database.commen.DatabasesConstants.EXAM_READ_ONLY;
import static com.example.tzy.demo.database.commen.DatabasesConstants.EXAM_READ_WRITE;
import static com.example.tzy.demo.http.common.HttpConstants.ATTR_USER_AUTH;

/**
 * @author: Tianzy
 * @create: 2020-11-01 21:30
 **/
@Api(tags = "03-科目测试")
@RestController
@RequestMapping("/api/v1")
public class SubjectController {

    private final SubjectRepository subjectRepository;

    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @GetMapping({
            "subjects",
            "student/subjects",
            "teacher/subjects"
    })
    @TargetDataSource(EXAM_READ_ONLY)
    public Response<BatchGet<SubjectEntity>> findSubjects(
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer perPage,
            @RequestParam(required = false, defaultValue = "false") boolean personal,
            @RequestParam(required = false) String name,
            @ApiIgnore @RequestAttribute(ATTR_USER_AUTH) CoreUserInfo userInfo) {
        List<SubjectEntity> entities =
                Lists.newArrayList(subjectRepository.findAll())
                        .stream()
                        .filter(entity -> !personal || userInfo.getId().equals(entity.getCreatorId()))
                        .filter(entity -> name == null || entity.getName().contains(name))
                        .sorted((o1, o2) -> -o1.getId().compareTo(o2.getId()))
                        .collect(Collectors.toList());
        pageNum = MoreObjects.firstNonNull(pageNum, 1);
        perPage = MoreObjects.firstNonNull(perPage, entities.size());
        Pagination.PagedRows<SubjectEntity> pagedRows = Pagination.fromFull(entities, perPage, pageNum);
        return Response.success(BatchGet.of(pagedRows.getRows(), pagedRows.getPagination()));
    }

    @PostMapping("teacher/subjects")
    @TargetDataSource(EXAM_READ_WRITE)
    public Response<Long> create(
            @Validated @RequestBody IdAndName<Long> subject,
            @ApiIgnore @RequestAttribute CoreUserInfo userInfo
    ){
        SubjectEntity entity = new SubjectEntity();
        entity.setName(subject.getName());
        entity.setCreatorId(userInfo.getId());
        entity.setCreatorName(userInfo.getNickname());
        return Response.success(subjectRepository.save(entity).getId());
    }
}
