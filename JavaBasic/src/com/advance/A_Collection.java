package com.advance;

import org.junit.Test;

import java.util.*;

/**
 * @Description: collection集合方法，遍历Collection集合Iterator,foreach
 * @create: 2021/12/29 - 17:13
 */
/* 一、集合框架的概述
 * 1.集合、数组都是对多个数据进行存储操作的结构，简称Java容器
 *  (此时的存储指的是内存层面的存储，不涉及到持久化的存储(.txt;.jpg;.avi;数据库))
 * 2.1 数组在存储多个数据方面的特点:
 *      > 一旦初始化以后，其长度就确定了
 *      > 数组一旦定义好，其元素的类型也就确定了。只能操作指定类型的数据了
 *       比如: String[] arr;int[] arr1; Object[] arr2;
 * 2.2 数组在存储多个数据方面的缺点:
 *      > 一旦初始化以后，其长度就不可修改
 *      > 数组中提供的方法非常有限，对于添加、删除、插入数据等操作，非常不便，
          同时效率不高
 *      > 获取数组中实际元素的个数的需求，数组没有现成的属性或方法可用
 *      > 数组存储数据的特点: 有序、可重复。对于无序、不可重复的需求，不能满足
 *
 * 二、集合框架
 *      |----Collection接口: 单列集合，用来存储一个一个的对象(基本数据类型用包装类)
 *          |----List接口: 存储有序的、可重复的数据 -> "动态"数组
 *              |----ArrayList、LinkedList、Vector
 *          |----Set接口: 存储无序的、不可重复的数据
 *              |----HashSet、LinkedHashSet、TreeSet
 *          |----Queue/Deque
 *
 *      |----Map接口: 双列集合，用来存储一对(key <-> value)映射的数据，(y=f(x))
 *              |----HashMap、LinkedHashMap、TreeMap、Hashtable、Properties
 *
 * 向Collection接口的实现类的对象中添加数据obj时，要求obj所在类要重写equals()
 */
public class A_Collection {
    /*
     * 三、Collection接口中的方法的使用:
     *   add(Object e): 将元素e添加到集合中
     *   size(): 获取添加的元素的个数
     *   addAll(Collection coll1): 将coll1集合中的元素添加到当前的集合中
     *   boolean remove(Object obj): 从当前集合中移除obj元素，成功返回true
     *   removeAll(Collection l): 移除和l的交集元素，如果任意元素被移除返回true
     *   retainAll(Collection l): 从该集合中删除所有未包含在指定集合中的元素
     *   equals()，每个Collection按顺序相等
     *   clear(): 清空集合元素
     *   isEmpty():判断当前集合是否为空
     *   contains(): contains调用的是equals()，判断内容是否存在
     *   containsAll(Collection l): 判断形参l中所有元素是否都存在于当前集合中
     *   hashCode(): 返回当前对象的哈希值
     *   toArray(): 集合 -> 数组Object[]
     *   Arrays.asList(): 数组 -> 集合
     *   iterator(): 返回Iterator接口的实例
     */
    @Test
    public void collectionTest() {
        Collection coll = new ArrayList();
        // add(Object e): 将元素e添加到集合coll中
        coll.add("AA");
        coll.add("BB");
        coll.add(123);// 自动装箱
        coll.add(new Date());

        // size(): 获取添加的元素的个数
        System.out.println(coll.size()); // 4

        // addAll(Collection coll1): 将coll1集合中的元素添加到当前的集合中
        Collection coll1 = new ArrayList();
        coll1.add(456);
        coll1.add("CC");
        // addAll()将另一集合元素加入到集合中
        coll.addAll(coll1);

        System.out.println(coll.size()); // 6
        System.out.println(coll);

        // boolean remove(Object obj): 从当前集合中移除obj元素，成功返回true
        coll.remove(456);
        // removeAll(Collection l): 移除和l的交集元素，如果任意元素被移除返回true
        // coll.removeAll(coll1);

        // retainAll(Collection l): 从该集合中删除所有未包含在指定集合中的元素
        // 交集: 获取当前集合和coll1集合的交集，并返回给当前集合，有删除则返回true
        // coll.retainAll(coll1);

        // equals()，每个Collection按顺序相等
        coll.equals(coll1);

        // clear(): 清空集合元素
        coll.clear();
        // isEmpty():判断当前集合是否为空
        // System.out.println(coll.isEmpty());

        // contains(): contains调用的是equals()，判断内容是否存在
        // 向Collection接口的实现类的对象中添加数据obj时，要求obj所在类要重写equals()
        coll.add(new String("hello"));
        System.out.println(coll.contains(new String("hello"))); // true
        // Person()没有重写，equals比较地址是否相等，返回false
        coll.add(new Person("张三", true));
        System.out.println(coll.contains(new Person("张三", true))); // false

        // containsAll(Collection l): 判断形参l中所有元素是否都存在于当前集合中
        System.out.println(coll.containsAll(coll1));

        // hashCode(): 返回当前对象的哈希值
        System.out.println(coll.hashCode());

        // toArray(): 集合 -> 数组Object[]
        Object[] arr = coll.toArray();

        // Arrays.asList(): 数组 -> 集合
        // int[] ll = new int[]{12, 34, 213}; // 不能是int，需要包装类数组
        // List a = Arrays.asList(ll);
        // System.out.println(a);
        List arr1 = Arrays.asList(123, 32);
        System.out.println(arr1);

        // iterator(): 返回Iterator接口的实例，用于遍历集合元素(见下)
    }

    /*
     * Iterator接口用于遍历集合元素 (主要用于遍历Collection，不包括Map)
     * 可以将Iterator对象看作一个指针，调用next()指针后移
     * 1.内部的方法: hasNext(), next()
     *   使用迭代器while(iterator.hasNext()){}遍历容器/集合元素
     * 2.集合对象每次调用iterator()方法都得到一个全新的迭代器对象，
         默认游标都在集合的第一个元素之前
     * 3.内部定义了remove()，可以在遍历的时候删除集合中的元素，
         此方法不同于集合直接调用remove()
         如果还未调用next()或在上一次调用next()方法之后已经调用了remove()方法
     */
    @Test
    public void iteratorTest() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(423);
        coll.add(new String("hello"));
        coll.add(false);

        Iterator iter = coll.iterator();

        // 遍历方式一: (很少用)
        // System.out.println(iter.next()); // 123
        // System.out.println(iter.next()); // 456
        // System.out.println(iter.next()); // hello
        // System.out.println(iter.next()); // false
        // System.out.println(iter.next()); // 越界异常

        // 遍历方式二: for()... (开发用的少)

        // * 遍历方式三: while()
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

        // remove()方法: 遍历时移除元素
        System.out.println(coll.size()); // 4
        iter = coll.iterator();
        while (iter.hasNext()) {
            if ("hello".equals(iter.next())) {
                iter.remove();
            }
        }
        System.out.println(coll.size());  // 3
    }


    /*
     * JDK5.0新增foreach增强for循环用于遍历集合和数组
     * 内部仍然调用迭代器
     *  for (集合中元素的类型 局部变量名 : 集合对象){
     *  }
     * 自动取位置上的元素赋给局部变量(无法通过局部变量改变数组值)
     */
    @Test
    public void foreachTest() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(423);
        coll.add(new String("hello"));
        coll.add(false);

        // 遍历集合
        for (Object o : coll) {
            System.out.println(o);
        }

        // 遍历数组
        int[] arr = new int[]{12, 3, 32, 84, 99};
        for (int i : arr) {
            System.out.println(i);
        }
    }
}

class Person {
    // 属性 field (成员变量)
    String name;
    boolean isMale;

    // 定义构造器
    public Person() { // 空参构造器
    }

    public Person(String name) {
        // 调用其他构造器时必须声明在当前构造器首行，且构造器内部最多声明一个其他构造器
        this(); // 相当于先创建一个空的结构器，随后补充额外代码
        this.name = name; // this关键字
    }

    public Person(String name, boolean isMale) {
        // 将name传输到Person(String name)结构器中
        this(name);
        // 然后再执行下面代码
        this.isMale = isMale;
    }

    // 方法 method (描述类中具有的功能)
    public void sleep() {
        System.out.println(name + "在睡觉");
    }

    // 可以通过方法调用属性，返回String类型数据
    public String getName() {
        return name;
    }

    public void talk(String language) { // language是形参
        System.out.println("语言是: " + language);
    }
}