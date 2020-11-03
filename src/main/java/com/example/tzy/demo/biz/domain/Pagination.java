package com.example.tzy.demo.biz.domain;

import com.example.tzy.demo.biz.domain.dto.Paginate;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.List;

/**
 * @author: Tianzy
 * @create: 2020-10-24 12:11
 **/
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Pagination extends Paginate {

    private long rowCount;

    public int getPageCount() {
        return (int) ((rowCount + getPerPage() - 1) / getPerPage());
    }

    public static <E> Pagination fromPartial(Collection<E> part, int pageNum, int rowCount){
        return Pagination.builder()
                .perPage(part.size())
                .pageNum(pageNum)
                .rowCount(rowCount)
                .build();
    }

    public static <E> PagedRows<E> fromFull(List<E> full,int perPage, int pageNum){
        Pagination pagination = Pagination.builder()
                .perPage(perPage)
                .pageNum(pageNum)
                .rowCount(full.size())
                .build();
        int begin = (pageNum-1)*perPage;
        int end = perPage+begin;
        int size = full.size();
        if(end>=size){
            if(begin>=size){
                begin=end=size;
            }else{
                end=size;
            }
        }
        return PagedRows.of(full.subList(begin,end),pagination);
    }

    public static <T> Pagination fromPage(PageInfo<T> page){
        return Pagination.builder()
                .perPage(page.getPages())
                .pageNum(page.getPageNum())
                .rowCount(page.getTotal())
                .build();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor(staticName = "of")
    public static class PagedRows<E>{
        private List<E> rows;
        private Pagination pagination;
    }
}
