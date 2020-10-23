package com.example.tzy.demo.database.repository;

import com.example.tzy.demo.database.entity.UserValidationEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserValidateRepository extends CrudRepository<UserValidationEntity,Long> {
    @Query(value = "select 1 as id,"+
            "exists(select 1 from core_user where username=:username) as exist_username,"+
            "exists(select 1 from core_user where mobile=:mobile) as exist_mobile,"+
            "exists(select 1 from core_user where email=:email) as exist_email;",
            nativeQuery = true
    )
    UserValidationEntity checkUserInfoDup(@Param("username") String username, String email, String mobile);
}
