package com.fundation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @content: 异常处理
 * @create 2021/12/24 - 0:57
 */

/*
 * 语法和逻辑错误不叫异常
 * 异常包括Error & Exception
 * Error: JVM无法解决的严重问题，JVM系统内部错误，资源耗尽StackOverflowError,OOM...
 * Exception: 空指针访问，读取不存在的文件，网络连接终端，数组角标越界...
 * 捕获异常最佳时期是编译期，但有的错误只有运行时发生(除数为0，数组下标越界等)
 * 分类: 编译时异常和运行时异常
 *
 * ① 异常体系结构:
 * java.lang.Throwable
 *          |-----java.lang.Error: JVM严重问题，不编写针对性代码处理
 *          |-----java.lang.Exception: 可以进行异常的处理
 *                |-----编译时异常(checked)
 *                      |-----IOException
 *                            |-----FileNotFoundException   // 文件未找到
 *                      |-----ClassNotFoundException        // 类型转换异常
 *                |-----运行时异常(unchecked / RuntimeException)
 *                      |-----NullPointerException          // 指针为null
 *                      |-----IndexOutOfBoundsException     // 角标越界
 *                      |-----ArrayIndexOutOfBoundException // 数组角标越界
 *                      |-----ClassCastException            // 转换类异常
 *                      |-----NumberFormatException         // 非数值类型"12ab"
 *                      |-----InputMismatchException        // 输入类型不匹配
 *                      |-----ArithmeticException           // 算数异常
 *                      |-----IllegalArgumentException      // 参数异常
 *                      |-----IllegalStateException         // 状态异常
 *                      |-----NegativeArraySizeException    // 数组负数
 *                      |-----NoSuchElementException        // 没有这个元素
 *                      |-----UnsupportedOperationException // 不支持的操作
 *                      |-----ClassFormatError              // 类格式错误
 *                      |-----InstantiationError            // 实例化错误
 *                      |-----IllegalAccessError            // 访问错误
 *                      |-----LinkageError                  // 链接错误
 *                      |-----NoClassDefFoundError          // 没有找到类定义
 *                      |-----NoSuchFieldError              // 没有这个字段
 *                      |-----NoSuchMethodError             // 没有这个方法
 *
 * ② 异常的处理:
 * 抓抛模型:
 *    过程一: "抛": 程序正常执行的过程中，一旦出现异常，就会在异常代码处生成一个
 *                 对应异常类的对象，并将此对象抛出，其后的代码不再执行
 *    过程二: "抓": 可以理解为异常处理方式
 *                 -> try-catch-finally (解决了异常)
 *                 -> throws + 异常类型 (报告异常并未解决异常问题)
 *
 * ③ try-catch-finally的使用:
 * I: try-catch:
 *    1.使用try将可能出现异常代码包装起来，在执行过程中，一旦出现异常就会生成一个对应
 *      异常类的对象，根据此对象的类型去catch中进行匹配
 *    2.一旦try中的异常匹配到某个catch时，就进入catch中进行异常的处理，一旦处理完成
 *      就跳出当前对try-catch结构(没有finally的情况)，继续执行其后的代码
 *    3.catch中的异常类型如果没有子父类关系，则catch声明顺序无所谓
 *      catch中的异常类型如果满足子父类关系，则子类异常一定声明在父类上方，否则报错
 *    4.在catch中可以加入String e.getMassage()显示异常信息，
 *      常用e.printStackTrace()打印堆栈信息(更全面的异常信息)
 *    5.注意: 在try结构中声明的变量为局部变量，离开try结构不能调用
 *    6.try-catch-finally结构可以嵌套
 *
 * II.finally:
 *    1.finally是可选的，且finally中声明的代码一定会被执行，
 *      即使try或catch中有return语句，或是catch中又产生了异常，finally也会执行，
 *      (try和catch中的return会在finally语句执行完成后执行)
 *      如果finally中也又return，会执行finally中的return
 *    2.像数据库连接，输入输出流，网络编程Socket等资源，JVM不能自动回收，需要手动
 *      进行资源的释放，此时的资源释放需要声明在finally中 (以防代码受到异常干扰不能
 *      执行)
 *
 * 总结: 使用try-catch-finally处理编译时异常，使得程序在编译时不再报错，
 *       但在运行时仍可能报错，相当于将编译时可能出现的异常延迟到运行时出现。
 *       开发中只用try结构考虑编译时异常(运行异常很常见，通常不针对运行异常处理)，
 *       针对编译时异常要考虑异常处理
 *
 *III. try-catch-finally结构
 * try {
 *      // 可能出现异常的代码
 * } catch(异常类型1，变量名1) {
 *      // 处理异常的方式1
 * } catch(异常类型2，变量名2){
 *      // 处理异常方式2
 * }
 * ...
 * // finally可选
 * (finally {
 *      // 一定会执行的代码
 * })
 */
public class E_throws {
    public static void main(String[] args) {
        // try-catch-finally
        try {
            String str = "123";
            str = "12ab";
            int num = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("出现数值转换异常");
            // 输出异常信息
            System.out.println(e.getMessage());
            // 异常的详细信息和异常出现的位置
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("出现异常了");
        } finally {
            System.out.println("运行结束");
        }

        // 读取文件的try
        FileInputStream fis = null;
        try {
            File file = new File("hello1.txt");
            fis = new FileInputStream(file);
            int data = fis.read();
            while (data != -1) {
                System.out.print((char) data);
                data = fis.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
