package com.example.tzy.demo.biz.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author: Tianzy
 * @create: 2020-11-03 23:00
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class IdAndName<ID> {
    private ID id;

    @NotBlank
    private String name;
}
