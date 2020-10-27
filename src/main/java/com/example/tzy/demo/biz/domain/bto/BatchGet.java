package com.example.tzy.demo.biz.domain.bto;

import com.example.tzy.demo.biz.domain.Pagination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: Tianzy
 * @create: 2020-10-24 12:04
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class BatchGet<E> {
    List<E> results;
    Pagination pagination;
}
