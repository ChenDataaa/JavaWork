package com.fundation;

/**
 * @content: 单例设计模式的第一种写法
 * @create 2021/12/22 - 22:10
 */
public class Singleton {
}


/**
 * 饿汉式
 * 创建单例的银行类
 */
class Bank{
    // 2.内部创建类的对象(也可以理解为类的属性)，要求此对象为静态
    private static Bank instance = new Bank();

    // 1.私有化类的构造器，避免在Bank类外造对象
    private Bank(){}

    // 3.提供公共的静态方法，返回类的对象，提供静态方法可以不用造对象外部调用此方法
    public static Bank getInstance(){
        return instance;
    }
}

/**
 * 懒汉式
 * 单例模式Order类
 */

class Order{
    // 2.声明当前类的实例，没有初始化，初始化在方法中提供
    private static Order instance = null;
    // 1.私有化构造器
    private Order(){}
    // 3.声明public，static的返回当前类对象的方法
    public static Order getInstance(){
        if(instance == null){
            instance = new Order();
        }
        return instance;
    }

}