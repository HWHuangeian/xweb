package com.huangweihan.xweb.core.utils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description
 *
 * @author: Administrator
 * @date: 2018/11/23 0023
 */
public class CollectionUtil {

    /**
     * 集合工具类是否为null或空
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map, boolean flag) {
        Map<K, V> sortMap = new LinkedHashMap<>();
        if(flag == true) {
            map.entrySet().stream()
                    .sorted((o1, o2) -> o1.getValue().compareTo(o2.getValue()))
                    .forEach(entry -> sortMap.put(entry.getKey(), entry.getValue()));
        } else {
            map.entrySet().stream()
                    .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                    .forEach(entry -> sortMap.put(entry.getKey(), entry.getValue()));
        }
        return sortMap;
    }


}
