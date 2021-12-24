package com.fundation;

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
 * --> try-catch-finally (解决了异常)
 * --> throws + 异常类型 (报告异常并未解决异常问题)
 */
public class E_throws {


}
