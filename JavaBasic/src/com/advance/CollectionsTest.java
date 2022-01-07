package com.advance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description: Collections工具类操作List, Set, Map，方法都是static静态方法
 * @create: 2021/12/31 - 13:51
 */

/*
 * reverse(List): 反转List中元素的顺序
 * shuffle(List): 对List集合元素进行随机排序
 * sort(List): 根据元素的自然顺序对指定List集合元素按升序排序
 * sort(List, Comparator): 根据指定的Comparator产生的顺序对List集合元素进行排序
 * swap(List, int, int): 将指定list集合中的i处元素和j处元素进行交换
 *
 * Object max(Collection): 根据元素的自然顺序，返回给定集合中的最大元素
 * Object max(Collection, Comparator): 根据Comparator指定的顺序，返回集合中的最大元素
 * Object min(Collection)
 * Object min(Collection, Comparator)
 * int frequency(Collection, Object): 返回指定集合中指定元素的出现次数
 * void copy(List dest, List src): 将src中的内容复制到dest中(dest size要大于等于src)
 * boolean replaceAll(List list, Object oldVal, Object newVal): 使用新值替换List对象
 *   的所有旧值
 *
 * 变为线程安全: 返回的l即为线程安全的List
 * List l = Collections.synchronizedList(l);
 */
public class CollectionsTest {
    @Test
    public void collectionsTest() {
        List list = new ArrayList();
        list.add(123);
        list.add(43);
        list.add(765);
        list.add(-97);
        list.add(0);
//        Collections.reverse(list);
//        Collections.shuffle(list);
//        Collections.sort(list);
//        Collections.swap(list,1,2);

//        List dest = new ArrayList();
//        报异常：IndexOutOfBoundsException("Source does not fit in dest")
//        Collections.copy(dest, list);
//        正确的写法: 在空List数组中存放null的Object
        List dest = Arrays.asList(new Object[list.size()]);
        Collections.copy(dest, list);

//        * 变为线程安全: 返回的l即为线程安全的List
//        * List l = Collections.synchronizedList(l);
//
    }
}
