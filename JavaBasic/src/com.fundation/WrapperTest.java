package com.fundation;

import org.junit.Test;

/**
 * @create 2021/12/22 - 11:49
 */
public class WrapperTest {
    int num = 10;

    @Test
    public void testEquals() {
        String s1 = new String("MMM");
        String s2 = new String("MMM");
        System.out.println(s1.equals(s2));
        System.out.println(num);
    }

    @Test
    /**
     * 基本数据类型类的测试
     */
    public void testType(){
        int i = 10;
        // 基本数据类型转包装类
        Integer j = i;
        // 包装类转基本数据类型
        //  int l = j.intValue();
        int l = j; // 自动装箱
        System.out.println(j);
        // 包装类转String
        String str = j.toString();
        System.out.println(str);
        // String转包装类
        Integer k = Integer.valueOf("122");
        System.out.println(k);
        // 基本转String
        String str1 = i + "";
        System.out.println(String.valueOf(i));
        // String转基本数据类型
        // 接收可以是int也可以是Integer包装类
        int kk = Integer.parseInt("123");
        Boolean b = Boolean.valueOf("tccccdsrue"); // 不报错，返回false

        Object o1 = true? 1: 2.0;
        System.out.println(o1); // 1.0 自动类型提升
    }


}
