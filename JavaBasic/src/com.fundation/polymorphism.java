package com.fundation;

/**
 * @content: 多态性练习
 * @create 2021/12/21 - 21:28
 */

class Base {
    int count = 111;

    public void show() {
        System.out.println("Base count: " + count);
    }
}

class Subclass extends Base {
    int count = 666;

    public void show() {
        System.out.println("sub count: " + count);
    }
}

public class polymorphism {
    public static void main(String[] args) {
        // 基础
        Subclass s = new Subclass();
        System.out.println(s.count); // 666
        s.show(); // 666

        // 多态性
        Base b = s;
        // * 多态性只适用于方法不适用于属性
        System.out.println(b.count); // 111
        // 方法是用的Subclass方法
        /*
         * 如果在Subclass类中未定义show()方法，则返回值为111
         * 然而定义了show()方法的子类重写了show()方法，
         * show方法中的count相当于this.count
         * 因此开发中不要定义重名的属性
         */
        b.show(); // 666
    }
}


