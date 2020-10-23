package com.example.tzy.demo.database.entity;

import com.sun.deploy.security.ValidationState;
import lombok.Data;

import javax.persistence.*;

/**
 * @author: Tianzy
 * @create: 2020-10-23 15:33
 **/
@Data
@Entity
@Table(name="core_user")
public class UserValidationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Long id;
    @Transient
    private boolean existUsername;
    @Transient
    private boolean existEmail;
    @Transient
    private boolean existMobile;

    public boolean validated(){
        return !(existUsername||existEmail||existMobile);
    }
}
