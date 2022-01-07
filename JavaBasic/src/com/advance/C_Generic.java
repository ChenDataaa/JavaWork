package com.advance;

import org.junit.Test;

import java.util.*;

/**
 * @Description: 泛型
 * @create: 2021/12/31 - 15:44
 */
/*
 * 泛型定义: 定义类，接口时通过一个标识表示类中某个属性的类型或某个方法的返回值及参数类型
 *
 * 泛型的使用
 * 一、JDK5.0新增的特性
 *     JDK7.0后类型推断，new后可不用写泛型类型:
 *     * Map<String, Integer> map = new HashMap<>()
 *
 * 二、泛型的使用:
 *  ① 集合接口或集合类在jdk5.0时都修改为带泛型的结构;
 *  ② 泛型可以有多个参数，用","隔开; 在实例化集合类时，可以指明具体的泛型类型;
 *  ③ 指明完以后，在集合类或接口中凡是定义类或接口时，内部结构(方法、构造器、属性等)
 *     使用到类的泛型的位置，都指定为实例化的泛型类型;
 *     例如: add(E e) --> 实例化以后: add(Integer e)
 *  ④ 注意点: 泛型的类型必须是类，不能是基本数据类型; 基本数据类型要用包装类替换;
 *  ⑤ 如果实例化时，没有指明泛型的类型。默认类型为java.lang.Object类型
 *  ⑥ 在Comparable中也可以使用泛型(不适用默认为Object)，不用判断instanceOf和强转类型
 *
 * 三、泛型类和接口:
 *  ① 如果定义了泛型，而实例化时没有指明类的泛型，则认为此泛型类型为Object类型
 *  ② 如果子类继承泛型父类时指明了泛型类型，则此子类实例化对象时不再需要指明泛型;
 *     class Sub extends Par<Integer> {}; (Sub不再是泛型类)
 *     反之，如果子类没有指明父类泛型类型，子类仍然是泛型类
 *     class Sub<E> extends Par<E> {}; (Sub仍然是泛型类)
 *     部分保留父类泛型:
 *     class Sub<T2> extends Par<Integer, T2> {};
 *     子类额外提供泛型参数
 *     class Sub<A, B> extends Par {};
 *     class Sub<A, B> extends Par<Integer, String> {};
 *     class Sub<A, B, T2> extends Par<Integer, T2> {}; // 部分保留
 *  ③ 泛型声明构造器时不加"< >"，实例化时要加"< >"
 *  ④ 静态方法不能用泛型(类的泛型在实例化时指定，静态结构早于实例化对象创建)
 *  ⑤ 异常类不能声明为泛型类
 *  ⑥ ⭐在类中创建泛型数组:
 *      T[] arr = new T[10]; // 错误(泛型只是类型不是类)
 *      T[] arr = (T[]) new Object[10]; // 正确
 *
 * 四、泛型方法:
 *  ① 在方法中出现了泛型的结构，泛型参数与类的泛型参数没有任何关系;
 *     (泛型方法所属的类是不是泛型类都没有关系)
 *  ② 泛型方法可以声明为静态的; (泛型参数是在调用方法时确定的，并非在实例化类时确定)
 *
 * 五、泛型在继承的体现:
 *     类A和类B是子父类关系，可以利用多态赋值，但Xxx<A>和Xxx<B>不具有子父类关系;
 *
 * 六、通配符 "?" 的使用: => G<?> 是所有 G<>类的通用父类
 *  ① List<?> list = null;
 *     List<Object> list1 = null;
 *     List<String> list2 = null;
 *     list = list1; // (多态)
 *     list = list2; // (多态)
 *  ② 通配符类型的集合不可添加数据:
 *     List<?> 除了null之外不可以添加任何数据
 *  ③ 允许读取数据get()
 *  ④ <? extends A> : 只允许泛型为A及A子类的引用调入
 *  ⑤ <? super A> : 只允许泛型为A及A父类的引用调入
 *  ⑥ <? extends Comparable> : 只允许泛型为实现Comparable接口的实现类的引用调入
 */
public class C_Generic {
    /*
     * 在Collection中使用泛型
     */
    @Test
    public void test1() {
/*
        // 类型不安全，没有限制数组存放类型，任何类对象都可添加进入
        ArrayList l = new ArrayList();
        l.add(12);
        l.add(88);
        l.add(98);
        l.add("ABC");
*/
        // 在集合中使用泛型:
        ArrayList<Integer> l = new ArrayList<Integer>();
        l.add(82);
        l.add(98);
        l.add(79);
        // 在编译时进行类型检查，保护数据安全
        // l.add("str")
/*
        遍历一: 通过增强for循环foreach
        for (Integer i : l) {
            // 避免了强转操作
            int j = i;
            System.out.println(j);
        }
*/
        // 遍历二: 通过Iterator
        Iterator<Integer> it = l.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    /*
     * 在Map中使用泛型
     */
    @Test
    public void test2() {
        // 集合中声明两个泛型
        Map<String, Integer> map = new HashMap<String, Integer>();
        // JDK7.0后: Map<String, Integer> map = new HashMap<>(); // 可省略
        map.put("Jerry", 19);
        map.put("Tome", 27);

        // Entry泛型
        Set<Map.Entry<String, Integer>> entry = map.entrySet();
        // Map的Iterator.next也是Entry类型
        Iterator<Map.Entry<String, Integer>> iterator = entry.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> e = iterator.next();
            String name = e.getKey();
            int i = e.getValue();
            System.out.println(name + ": " + i);
        }
    }

    @Test
    public void orderTest() {
//        如果定义了泛型，而实例化时没有指明类的泛型，则认为此泛型类型为Object类型
//        Order order = new Order();
//        建议实例化时指明类的泛型
        Order<String> order = new Order<>("AA", 1001, "BB");
    }

    /**
     * @Description: 通配符"?"的使用
     */
    @Test
    public void test3() {
        List<Object> list1 = null;
        List<String> list2 = null;
        List<?> list = null;
        // list = list1;
        // list = list2;
        // 编译通过

        List<String> list3 = new ArrayList<>();
        list3.add("AA");
        list3.add("BB");
        list3.add("CC");
        list = list3;
        // 添加(写入)：对于List<?>就不能向其内部添加数据。
        // 除了添加null之外。
        // list.add("DD"); // 编译不通过
        // 只能add null
        list.add(null);

        // 获取(读取)：允许读取数据，读取的数据类型为Object。
        Object o = list.get(0);
        System.out.println(o);

        /*
         * 有限制条件的通配符
         * ① <? extends A> : 只允许泛型为A及A子类的引用调入; 只可以add null和A
         * ② <? super A> : 只允许泛型为A及A父类的引用调入; 可以add A及A子类
         * ③ <? extends Comparable> : 只允许泛型为实现Comparable接口的实现类的引用调入
         */

    }


}

/**
 * @Description: 自定义泛型类Order
 */
class Order<T> {
    String orderName;
    int orderId;
    // 类的内部结构就可以使用类的泛型
    T orderT;

    public Order() {
        // 编译不通过
        // T[] arr = new T[10];
        // 编译通过
        T[] arr = (T[]) new Object[10];
    }

    public Order(String orderName, int orderId, T orderT) {
        this.orderName = orderName;
        this.orderId = orderId;
        this.orderT = orderT;
    }

    // 如下的三个方法都不是泛型方法
    public T getOrderT() {
        return orderT;
    }

    public void setOrderT(T orderT) {
        this.orderT = orderT;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderName='" + orderName + '\'' +
                ", orderId=" + orderId +
                ", orderT=" + orderT +
                '}';
    }

    // * 泛型方法: 将E类型数组copy到List()中
    public <E> List<E> copyFromArrayToList(E[] arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }
}
