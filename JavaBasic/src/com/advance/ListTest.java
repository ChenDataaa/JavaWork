package com.advance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: List接口: 存储有序，可重复的数据("动态"数组)
 * @create: 2021/12/29 - 23:03
 */

/*
 * 一、List接口框架(相比Collection类，List有索引，可以普通for循环get(index))
 *    |----Collection接口: 单列集合，用来存储一个一个的对象
 *          |----List接口: 存储有序的、可重复的数据("动态"数组)，替换原有的数组
 *              |----ArrayList: List接口的主要实现类，线程不安全的，效率高，
 *                              底层使用Object[] elementData存储
 *              |----LinkedList: 对于频繁的插入，删除操作，效率比ArrayList高，
 *                               底层使用双向链表存储
 *              |----Vector: List接口的古老实现类，线程安全的，效率低，
 *                           底层使用Object[] elementData存储
 *
 *  开发已弃用vector，大多使用ArrayList，即使线程不安全也会通过Collections中
 *  SynchronizedList使ArrayList变为线程安全
 *
 *  面试题: 三者异同点:
 *  相同点: 三个类都是实现了List接口，存储数据的特点相同: 存储有序的、可重复的数据
 *  不同点: 见上
 *
 * 二、ArrayList的源码分析:
 * ① jdk7情况下:
 *    // 默认实例化时就创建长度为10的Object[]数组elementData
 *    ArrayList list = new ArrayList();
 *    另外还有ArrayList(int initialCapacity)构造器可以初始化ArrayList长度
 *    list.add(123); // elementData[0] = new Integer(123);
 *    list.add(11); // 如果此次的添加导致底层elementData数组容量不够，则扩容。
 *    默认情况下，扩容为原来的容量的1.5倍，同时需要将原有数组中的数据复制到新的数组中。
 *    结论: 建议开发中使用带参的构造器: ArrayList list = new ArrayList(int capacity)
 * ② jdk8中ArrayList的变化:
 *    // 底层Object[] elementData初始化为{}，没有创建长度为10的数组，节省内存
 *    ArrayList list = new ArrayList();
 *    // 第一次调用add()时，底层才创建了长度10的数组，并将数据添加到elementData[0]
 *    list.add(123);
 *    后续的添加和扩容操作与jdk7无异
 * ③ 小结: jdk7中的ArrayList的对象的创建类似于单例的饿汉式，而jdk8中的ArrayList的对象
 *         的创建类似于单例的懒汉式，在需要时才创建数组，延迟了数组的创建，节省内存。
 *
 * 三、LinkedList的源码分析:
 * // 内部声明了Node类型结构，用来存储对象，Node类型有first和last属性，默认值为null
 * ① LinkedList list = new LinkedList();
 *    list.add(123); // 将123封装到Node中，创建了Node对象
 *    其中，Node定义为: 体现了LinkedList的双向链表的说法
 *    private static class Node<E> {
 *        E item;
 *        Node<E> next;
 *        Node<E> prev;
 *        Node(Node<E> prev, E element, Node<E> next) {
 *            this.item = element;
 *            this.next = next;
 *            this.prev = prev;
 *        }
 *    }
 *
 * 四、Vector的源码分析: jdk7和jdk8中通过Vector()构造器创建对象时，
 *     底层都创建了长度为10的数组; 在扩容方面，默认扩容为原来的数组长度的2倍。

 */

/*
 * 五、List接口中的常用方法
 * void add(int index, Object ele): 在index位置插入ele元素
 * boolean addAll(int index, Collection eles): 从index位置开始将eles中所有元素添加进来
 * Object get(int index): 获取指定index位置的元素
 * int indexOf(Object obj): 返回obj在集合中首次出现的位置
 * int lastIndexOf(Object obj): 返回obj在当前集合中末次出现的位置
 * // remove()重载，可以按照对象去删，可以按照index去删
 * Object remove(int index): 移除指定index位置的元素，并返回此元素
 * Object set(int index, Object ele): 设置指定index位置的元素为ele
 * List subList(int fromIndex, int toIndex): 返回从fromIndex到toIndex位置的子集合
 *
 * 总结: 常用方法
 * 增: add(Object obj)
 * 删: remove(int index) / remove(Object obj)
 * 改: set(int index, Object ele)
 * 查: get(int index)
 * 插: add(int index, Object ele)
 * 长度: size()
 * 遍历: ① Iterator迭代器方式 ② 增强for循环 ③ 普通的循环(List有索引get(index))

 */
public class ListTest {
    @Test
    public void listTest() {
        ArrayList l = new ArrayList();
        l.add(123);
        l.add(452);
        l.add("AA");
        // 将"BB"插入到索引为"1"的位置
        l.add(1, "BB");
        // 插入list所有元素
        List l2 = Arrays.asList(1, 2, 3);
        l.addAll(2, l2);
        // get索引处的元素
        l.get(4);
        // remove()两种
        l.remove(3);
        l.remove("AA");
        l.set(1, "cc");
    }
}
