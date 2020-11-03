package com.example.tzy.demo.biz.domain.dto.request;

import com.example.tzy.demo.biz.domain.dto.Paginate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author: Tianzy
 * @create: 2020-10-25 10:36
 **/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class UserQueryRequest extends Paginate {
    private String name;
    private Short roleId;
}
