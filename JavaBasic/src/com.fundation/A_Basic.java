package com.fundation;
//* 先使用javac.exe编译.java文件源码，编译以后会生成字节码class文件

//* 随后再使用java.exe运行编译后的.class文件
//* java文件可以有多个class，但只能有一个public
//* 且public类名要和文件名一致

// 标识符 identifier，凡是可以起名的都叫标识符（变量，类名，接口名，方法名，包名...）

/*
* 包名：全部小写 aaabbbccc
* 类名，接口名：首字母大写 AaaBbbCcc
* 变量名，方法名：首字母小写后大写 aaaBbbCcc
* 常量名：全部大写 AAA_BBB_CCC

* 数据类型：
* 1.基本数据类型：byte(1字节 8bits), short, int, long, float, double, char, boolean
* 2.引用数据类型：class, enum, array, interface (接口)
*/
public class A_Basic {
    public static void main(String[] args) // 程序入口
    {
        // # 1.基本数据类型
        // print()最后不换行，println()最后换行
        System.out.println("Hello World!");
        // * 整型Java默认int类型变量，声明long型变量要以'l'或'L'结尾
        // long a = 1234896L;
        // * 浮点型Java默认double类型变量，声明float型变量要以'f'或'F'结尾

        // 字符型 char
        // char c = 'a';
        System.out.print("hello" + "\n" + "world" + "\n");
        // Java boolean类型不支持0，1转换
        // * byte, char, short同级别，当此三种类型彼此做运算时会提升为int类型
        // * 即使是byte+byte类型也会提升为int类型
        byte a = 10, b = 20;
        byte ab = (byte) (a + b);
        System.out.println(ab);
        // # 2.赋值运算
        // short 和 byte 类不可以相互运算
        // short sh = 10;
        // sh = sh + 1; // 错误
        // sh += 1; // 正确

        // * String类型，可以做连接操作
        String str1 = "Hello ";
        str1 += "World ";
        str1 += 50000;
        str1 += true;
        System.out.println(str1);
        // 练习
        System.out.println('*' + '*'); // 84
        System.out.println('*' + "*"); // **
        System.out.println('a' + 5 + " hello"); // 102 hello
        // 不可以直接强行转换String类型为int类型

        // # 3.进制转换
        // * 二进制：0b/0B开头；八进制：0开头；十六进制：0x/0X开头
        // int a = 0123; // 8进制 oct
        // int a = 0x12; // 16进制 hex
        // int a = 0b10; // 2进制 bin

        // # 4.运算符
        // 两整数相除默认是整数除法，不会发生浮点数
        System.out.println(5 / 2); // 2
        System.out.println(5 / 2.0); // 2.5
        // * 取余运算符：%, 结果的符号和被取余数的符号相同
        System.out.println(12 % 5); // 2
        System.out.println(12 % -5); // 2
        System.out.println(-12 % 5); // -2
        System.out.println(-12 % -5); // -2

        // # 5.比较运算符 instanceof (其他包括 >= <= > < != ==)
        // instanceof 对象是否是类的子类
        System.out.println("hello" instanceof String);

        // # 6.逻辑运算符 & && | || ! ^(异或)
        // (如果对比的是逻辑值则是逻辑运算符，如果是数值则是位运算符)
        // * 建议使用 &&，||，短路运算，如果符号前满足或不满足条件，则不再检查后面的条件

        // # 7.位运算符 & | ^ ~ << >> >>> (只适用于int型)
        // << n: 左移，右边补0，相当于乘以2的n次方
        // >> n: 右移，左边补0，相当于除以2的n次方

        // # 8.三元运算符 (条件)? true表达式 : false表达式
        int m = 5, n = 3;
        int q = (m > n) ? 1 : 0;
        System.out.println(q);

        // # 9.if-else
        if (m > n) {
            System.out.println("m>n");
        } else if (m == n) {
            System.out.println("m=n");
        } else {
            System.out.println("m<=n");
        }
    }
}