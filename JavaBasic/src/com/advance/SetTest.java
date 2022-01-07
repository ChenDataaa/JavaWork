package com.advance;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.*;

/**
 * @Description: Set(): HashSet, LinkedHashSet, TreeSet
 * @create: 2021/12/30 - 11:17
 */

/*
 * 一、Set接口的框架:
 * |----Collection接口: 单列集合，用来存储一个一个的对象
 *      |----Set接口: 存储无序的、不可重复的数据
 *           在Set()中添加的对象，其所在的类一定要重写hashCode()和equals()
 *           |----HashSet: Set接口的主要实现类; 线程不安全的，可以存储null值
 *                |----LinkedHashSet: 是HashSet的子类，在原有HashSet基础上额外添加了
 *                                    双向链表记录添加对象的先后顺序。遍历其内部数据时，
 *                                    可以按照添加的顺序遍历(方便频繁遍历操作)
 *
 *           |----TreeSet: ① 底层使用红黑树存储，可以按照添加对象的指定属性进行排序，
 *                            遍历时默认从小到大排序遍历;
 *                         ② 添加的对象必须是同一类的对象，否则报错;
 *                         ③ Comparable,Comparator接口在TreeSet中有体现，如果添加对象
 *                            的类未重写compareTo方法会报错;
 *                         ④ TreeSet()有另一个定制排序构造器TreeSet(Comparator c);
 *                         ⑤ 在TreeSet判断元素是否相同的标准不再是equals()，而是判断添
 *                            加的对象compareTo() == 0，如果比较时compareTo() == 0，
 *                            则认为元素重复不会添加到TreeSet中;
 *
 *
 * 说明:
 *   ① 对于频繁的遍历操作，LinkedHashSet效率高于HashSet
 *   ② Set接口中没有额外定义新的方法，使用的都是Collection中声明过的方法
 *
 * 二、⭐向Set(主要指: HashSet、LinkedHashSet)中添加的对象，其所在的类一定要
 *     重写hashCode()和equals()。重写的hashCode()和equals()尽可能保持一致性:
 *     相等的对象(equals的对象)必须具有相等的散列码
 *     (不重写hashCode()则会继承Object类的hashCode()方法被随机计算)
 * 重写两个方法的小技巧: 对象中用作equals()方法比较的Field，都应该用来计算hashCode值

/ *
 * 一、Set: 存储无序的、不可重复的数据
 *    以HashSet为例说明:
 * ① 无序性: (不等于随机性)，存储的数据在底层数组中并非按照数组索引的顺序添加，
 *    而是根据数据的哈希值决定的。
 *
 * ② 不可重复性: 保证添加的元素按照equals()判断时，不能返回true
 *    即: 相同的元素只能添加一个。
 *
 * 二、Set()添加元素的过程: 以HashSet为例:
 *    向HashSet中添加元素a，首先调用元素a所在类的hashCode()方法，计算元素a的哈希值，
 *    此哈希值通过某种算法计算出在HashSet底层数组中的存放位置(即为: 索引位置)，
 *    Ⅰ->判断数组此位置上是否已经有元素:
 *       如果此位置上没有其他元素，直接将元素a放入该位置，元素a添加成功 -> (情况①)
 *       如果此位置上有其他元素b (或以链表形式存在的多个元素)，
 *       Ⅱ->则比较元素a与元素b(或链表中每个元素)的hash值:
 *           如果hash值不相同，则元素a添加成功，将a添加到该位置的链表中 -> (情况②)
 *           如果hash值相同，
 *              Ⅲ->进而需要调用元素a所在类的equals()方法:
 *                  equals()返回true，元素a添加失败
 *                  equals()返回false，则元素a添加成功 -> (情况③)
 *
 * 三、总结:
 *    对于添加成功的②，③而言: 元素a与已经存在指定索引位置上数据以链表的方式存储
 *    jdk7: 元素a放到数组中，指向原来的元素。
 *    jdk8: 原来的元素在数组中，指向元素a
 *    总结: 七上八下
 *    HashSet底层: 数组 + 链表的结构
 */
public class SetTest {
    @Test
    public void treeSetComparatorTest() {
        Comparator com = new Comparator() {
            // 按照年龄从小到大排列
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof User && o2 instanceof User) {
                    User u1 = (User) o1;
                    User u2 = (User) o2;
                    return Integer.compare(u1.age, u2.age);
                } else {
                    throw new RuntimeException("输入的数据类型不匹配");
                }
            }
        };

        // TreeSet(Comparator com)构造器
        TreeSet set = new TreeSet(com);
        set.add(new User("Tom", 12));
        set.add(new User("Jerry", 32));
        set.add(new User("Jim", 2));
        set.add(new User("Mike", 65));
        set.add(new User("Mary", 33));
        set.add(new User("Jack", 33));
        set.add(new User("Jack", 56));

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }


    /*
     * 关于Set()的练习:
     *    注意Set()添加元素时首先比较的是HashCode()再比较equals()!
     */
    @Test
    public void test() {
        HashSet set = new HashSet();
        User p1 = new User("AA", 1001);
        User p2 = new User("BB", 1002);

        set.add(p1);
        set.add(p2);
        // [User{name='AA', age=1001}, User{name='BB', age=1002}]
        System.out.println(set);

        p1.name = "CC";
        set.remove(p1);
        // [User{name='CC', age=1001}, User{name='BB', age=1002}]
        System.out.println(set);
        set.add(new User("CC", 1001));
        // [User{name='CC', age=1001}, User{name='CC', age=1001},
        // User{name='BB', age=1002}]
        System.out.println(set);
        set.add(new User("AA", 1001));
        // [User{name='CC', age=1001}, User{name='CC', age=1001},
        // User{name='AA', age=1001}, User{name='BB', age=1002}]

        System.out.println(set);
    }
}


/**
 * @Description: 用于Set()类
 * @create: 2021/12/30 - 14:02
 */
class User implements Comparable {
    String name;
    int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("User equals()....");
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        User user = (User) o;

        if (age != user.age)
            return false;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        // return name.hashCode() + age; // 也可以但是容错低
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    // 按照姓名从大到小排列,年龄从小到大排列
    @Override
    public int compareTo(@NotNull Object o) {
        if (o instanceof User user) {
            int compare = this.name.compareTo(user.name);
            if (compare != 0) {
                return compare;
            } else {
                return Integer.compare(this.age, user.age);
            }
        } else {
            throw new RuntimeException("输入的类型不匹配");
        }

    }
}