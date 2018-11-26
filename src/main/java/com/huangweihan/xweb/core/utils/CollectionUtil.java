package com.huangweihan.xweb.core.utils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 集合工具类
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

    /**
     * 对 Map 按 key 排序
     * @param map
     * @param flag true: 正序 false: 倒序
     * @param <K>
     * @param <V>
     * @return
     */
     public static <K extends Comparable<? super K>, V> Map<K, V> sortedByKey(Map<K, V> map, boolean flag) {
         return doSortedByKey(map, flag, Integer.MAX_VALUE);
     }

    /**
     * 对 Map 按 key 排序
     * @param map
     * @param flag true: 正序 false: 倒序
     * @param limit 排序后截取前limit个
     * @param <K>
     * @param <V>
     * @return
     */
     public static <K extends Comparable<? super K>, V> Map<K, V> sortedByKeyLimit(Map<K, V> map, boolean flag, int limit) {
         return doSortedByKey(map, flag, limit);
     }

     /**
      * 对 Map 按 value 排序
      * @param map
      * @param flag true: 正序 false: 倒序
      * @param <K>
      * @param <V>
      * @return
      */
     public static <K, V extends Comparable<? super V>> Map<K, V> sortedByValue(Map<K, V> map, boolean flag) {
         return doSortedByValue(map, flag, Integer.MAX_VALUE);
     }

     /**
      * 对 Map 按 value 排序
      * @param map
      * @param flag true: 正序 false: 倒序
      * @param limit 排序后截取前limit个
      * @param <K>
      * @param <V>
      * @return
      */
     public static <K, V extends Comparable<? super V>> Map<K, V> sortedByValueLimit(Map<K, V> map, boolean flag, int limit) {
         return doSortedByValue(map, flag, limit);
     }

     private static <K extends Comparable<? super K>, V> Map<K, V> doSortedByKey(Map<K, V> map, boolean flag, int limit) {
         Map<K, V> sortMap = new LinkedHashMap<>();
         if (flag) {
             map.entrySet().stream()
                     .sorted(Map.Entry.comparingByKey())
                     .limit(limit)
                     .forEachOrdered(e -> sortMap.put(e.getKey(), e.getValue()));
         } else {
             map.entrySet().stream()
                     .sorted(Map.Entry.<K, V>comparingByKey().reversed())
                     .limit(limit)
                     .forEachOrdered(e -> sortMap.put(e.getKey(), e.getValue()));
         }
         return sortMap;
     }

     private static <K, V extends Comparable<? super V>> Map<K, V> doSortedByValue(Map<K, V> map, boolean flag, int limit) {
         Map<K, V> sortMap = new LinkedHashMap<>();
         if (flag) {
             map.entrySet().stream()
                     .sorted((o1, o2) -> o1.getValue().compareTo(o2.getValue()))
                     .limit(limit)
                     .forEach(entry -> sortMap.put(entry.getKey(), entry.getValue()));
         } else {
             map.entrySet().stream()
                     .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                     .limit(limit)
                     .forEach(entry -> sortMap.put(entry.getKey(), entry.getValue()));
         }
         return sortMap;
     }

}
