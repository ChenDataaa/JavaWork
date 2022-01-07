package com.fundation;

import org.junit.Test;

/**
 * @Description: enum, Enum枚举类的使用
 * @create: 2021/12/29 - 12:09
 */

/*
 * 一、枚举类的使用:
 *    ① 枚举类的对象只有确定的有限个
 *    ② 当需要定义一组常量时(例如状态)，强烈建议使用枚举类
 *    ③ 如果枚举类中只有一个对象，则可以作为单例模式的实现方式
 *    ④ 枚举类可以实现接口，但不能继承类
 *    ⑤ 枚举类继承的是Enum类，而不是Object类

 * 二、定义枚举类:
 *    方式一: jdk5.0之前，自定义枚举类
 *    方式二: jdk5.0，可以使用enum关键字定义枚举类
 *           定义的枚举类默认继承于java.lang.Enum类，而非java.lang.Object
 *           默认toString()方法打印当前对象名称(可以重写)

 * 三、Enum类中的常用方法:
 *    values()方法: 返回枚举类型的对象数组。该方法可以遍历所有的枚举值
 *    valueOf(String str): 找到执行名称的枚举类对象，要求字符串必须是枚举类对象
          的"名字"。如不是，会有运行时异常IllegalArgumentException
 *    toString(): 返回当前枚举类对象常量的名称

 * 四、使用enum关键字定义的枚举类实现接口的情况
 *    情况一: 实现接口，在enum类中实现抽象方法
 *    情况二: 让枚举类的对象分别实现接口中的抽象方法
 *            (SPRING(){public void xxx(){}}) // 让每个对象不同方法
 */

/*
 * JDK5.0之后方法: enum关键字定义枚举类
 */
enum Season1 {
    // 1.先定义对象
    // 多个对象之间用","隔开，末尾";"
    /*
     * 如果让每个枚举对象有自己的方法针对此对象的操作:
     *  SPRING("春天", "春暖花开"){
     *      public void method(){
     *          // 设置方法体
     *      }
     *  }
     * */
    SPRING("春天", "春暖花开"),
    SUMMER("夏天", "夏日炎炎"),
    AUTUMN("秋天", "秋高气爽"),
    WINTER("冬天", "冰天雪地");

    // 2.如果枚举类有属性一定private final修饰(对象是常量属性也常量)
    private final String seasonName;
    private final String seasonDes;

    // 3.私有化类的构造器，并给对象属性赋值
    private Season1(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDes = seasonDesc;
    }

    //4.1 其他诉求1: get()方法获取枚举类对象的属性(没有set())
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDes;
    }
}

public class K_enum {
    @Test
    public void seasonTest() {
        Season spring = Season.SPRING;
        System.out.println(spring);
        System.out.println(spring.getSeasonName());
    }

    @Test
    public void season1Test() {
        Season1 spring = Season1.SPRING;
        // 1.enum继承的是Enum类，而非Object类
        // 没有重写过的enum对象默认toString是对象名
        System.out.println(spring);  // SPRING
        System.out.println(Season1.class.getSuperclass()); // java.lang.Enum

        // 2.values()遍历enum数组对象，查看枚举类状态
        Season1[] seasons = Season1.values();
        for (int i = 0; i < seasons.length; i++) {
            System.out.println(i + ": " + seasons[i]);
        }
        // 查看线程状态
        Thread.State[] states = Thread.State.values();
        for (int i = 0; i < states.length; i++) {
            System.out.println("线程状态: " + states[i]);
        }
        // 3.valueOf(String str): 找str名称的枚举类对象
        Season1 winter = Season1.valueOf("WINTER");
        System.out.println(winter);

        // 4.toString()
        String winterStr = winter.toString();
        System.out.println(winterStr);
    }
}

/*
 * JDK5.0之前方法: 自定义枚举类
 */
class Season {
    //3.提供当前枚举类的多个对象: public static final修饰
    public static final Season SPRING = new Season("春天", "春暖花开");
    public static final Season SUMMER = new Season("夏天", "夏日炎炎");
    public static final Season AUTUMN = new Season("秋天", "秋高气爽");
    public static final Season WINTER = new Season("冬天", "冰天雪地");

    // 1.如果枚举类有属性一定private final修饰(对象是常量属性也常量)
    private final String seasonName;
    private final String seasonDes;

    // 2.私有化类的构造器，并给对象属性赋值
    private Season(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDes = seasonDesc;
    }

    //4.1 其他诉求1: get()方法获取枚举类对象的属性(没有set())
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDes;
    }

    // 4.2 其他诉求2: toString()方法
    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDes + '\'' +
                '}';
    }
}
