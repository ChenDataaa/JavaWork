package com.advance;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Description: 调用运行时类中指定的结构: 属性,方法,构造器
 * @create: 2022/01/03 - 22:33
 */
public class N_Reflection1 {

    /**
     * 调用运行时类中指定的结构: 属性、方法、构造器
     */

    /*
     * 不需要掌握
     */
    @Test
    public void testField() throws Exception {
        Class<Pearson> clazz = Pearson.class;
        // 创建运行时类的对象
        Pearson p = clazz.getDeclaredConstructor().newInstance();
        // 获取指定的属性: 要求运行时类中属性声明为public
        // 通常不采用此方法
        Field id = clazz.getField("id");
        /*
         * 设置当前属性的值
         * 变量名.set():参数1: 指明设置哪个对象的属性 参数2: 将此属性值设置为多少
         */
        id.set(p, 1001);
        /*
         * 获取当前属性的值
         * get():参数1: 获取哪个对象的当前属性值
         */
        int pId = (int) id.get(p);
        System.out.println(pId);
    }

    /*
     * 如何操作运行时类中的指定的属性 -- 需要掌握
     */
    @Test
    public void testField1() throws Exception {
        Class<Pearson> clazz = Pearson.class;
        // 创建运行时类的对象
        Pearson p = clazz.getDeclaredConstructor().newInstance();
        // 1.getDeclaredField(String fieldName): 获取运行时类中指定变量名的属性
        // (全权限，包括private)
        Field name = clazz.getDeclaredField("name");
        // 2.保证当前属性是可访问的
        name.setAccessible(true);
        // 3.获取、设置指定对象的此属性值
        name.set(p, "Tom");
        System.out.println(name.get(p));
    }

    /*
     * 如何操作运行时类中的指定的方法 -- 需要掌握
     */
    @Test
    public void testMethod() throws Exception {
        Class<Pearson> clazz = Pearson.class;
        // 创建运行时类的对象
        Pearson p = clazz.getDeclaredConstructor().newInstance();
        /*
         * 1.获取指定的某个方法
         * getDeclaredMethod(指明获取的方法的名称, 指明获取的方法的形参列表)
         */
        Method show = clazz.getDeclaredMethod("show", String.class);
        // 2.保证当前方法是可访问的
        show.setAccessible(true);
        /*
         * 3.调用方法的invoke(方法的调用者, 给方法形参赋值的实参)
         * invoke()也有返回值，即为对应类中调用的方法的返回值
         * 如果调用的运行时类中的方法没有返回值，则此invoke()返回null
         */
        Object returnValue = show.invoke(p, "hello");
        System.out.println(returnValue);

        // 静态方法调用:
        // private static void showDesc()
        Method showDesc = clazz.getDeclaredMethod("showDesc");
        showDesc.setAccessible(true);
        // 静态invoke中不需要指明调用的对象
        // Object returnVal = showDesc.invoke(null);
        Object returnVal = showDesc.invoke(Person.class);
        System.out.println(returnVal);// null
    }

    /*
     * 如何调用运行时类中的指定的构造器
     */
    @Test
    public void testConstructor() throws Exception {
        Class<Pearson> clazz = Pearson.class;

        // private Person(String name)
        /*
         * 1.获取指定的构造器
         * getDeclaredConstructor():参数：指明构造器的参数列表
         */

        Constructor constructor = clazz.getDeclaredConstructor(String.class);

        // 2.保证此构造器是可访问的
        constructor.setAccessible(true);

        // 3.调用此构造器创建运行时类的对象
        Person per = (Person) constructor.newInstance("Tom");
        System.out.println(per);

    }

}