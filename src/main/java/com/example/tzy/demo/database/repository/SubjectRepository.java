package com.example.tzy.demo.database.repository;

import com.example.tzy.demo.database.entity.SubjectEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubjectRepository extends CrudRepository<SubjectEntity,Long> {

    @Override
    @Query("select s from SubjectEntity as s where s.del = 0")
    Iterable<SubjectEntity> findAll();

    List<SubjectEntity> findAllByNameContains(String name);
}
