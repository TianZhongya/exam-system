package com.example.tzy.demo.common.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author: Tianzy
 * @create: 2020-10-25 10:53
 **/
public class ConvertUtils {

    private ConvertUtils(){}

    public static <T,R> List<R> extractList(Collection<T> collection, Function<? super T,R> func){
        return collection.stream().map(func).collect(Collectors.toList());
    }

    public static <C, K, U> Map<K, U> extractMap(Collection<C> collection,
                                                 Function<? super C, ? extends K> keyMapper,
                                                 Function<? super C, ? extends U> valueMapper) {
        return collection.stream().collect(Collectors.toMap(keyMapper, valueMapper));
    }
}
