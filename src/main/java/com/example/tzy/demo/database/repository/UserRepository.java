package com.example.tzy.demo.database.repository;

import com.example.tzy.demo.database.entity.CoreUserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<CoreUserEntity,Long> {
    CoreUserEntity findByUsernameAndPasswordAndIsDel(String username,String password,Short isDel);
    CoreUserEntity findByEmailAndPasswordAndIsDel(String email,String password,short isDel);
    CoreUserEntity findByMobileAndPasswordAndIsDel(String mobile,String password,short isDel);
}
