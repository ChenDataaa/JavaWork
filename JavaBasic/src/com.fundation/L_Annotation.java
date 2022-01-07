package com.fundation;

/**
 * @Description: Annotation注解类(JDK5.0之后新增)
 * @create: 2021/12/29 - 14:24
 */

/*
 * 框架 = 注解 + 反射 + 设计模式
 * ① Annotation: 代码中特殊标记(@Xxx)，在编译，类加载，运行时被读取，并执行相应的
      处理，程序员可以在不改变原有逻辑的情况下, 在源文件中嵌入一些补充信息。
 * ② 在JavaSE中，注解的使用目的比较简单，例如标记过时的功能，忽略警告等。
      在JavaEE/Android中注解占据了更重要的角色，例如用来配置应用程序的任何切面，代替
      JavaEE旧版中所遗留的繁冗
 */
/*
 * ① 生成文档时注解:
 *    @author; @version; @see(相关主题); @since(那个版本开始新增);
 *    @param 形参名 形参类型 形参说明; @return 返回值类型 返回值说明;
 *    @exception 异常类型 异常说明
 *
 * ② 编译时格式检查(JDK内置的三个基本注解)
 *    @Override: 重写父类/接口方法
 *    @Deprecated: 表示所修饰的元素(类，方法)过时
 *    @SuppressWarnings: 抑制编译器警告，unused...
 *
 * ③ 跟踪代码依赖性，实现替代配置文件功能
 *
 * ④ 如何自定义注解(用的少)，参照SuppressWarning定义，注解声明为@interface
 *     1.注解声明为：@interface
 *     2.内部定义成员，通常使用value表示
 *     3.可以指定成员的默认值，使用default定义
 *     4.如果自定义注解没有成员，表明是一个标识作用
 *     // 后续用反射读取注解操作
 *
 *    @interface MyAnnotation{
 *         String value(); // 一个成员变量
 *         // String value() default "hello"; // 默认值
 *         String[] value(); // 多个成员变量
 *     }
 *
 *    如果注解有成员，在使用注解时，需要指明成员的值
 *    自定义注解必须配上注解的信息处理流程(使用反射)才有意义
 *    自定义注解通过都会指明两个元注解：Retention、Target

   // 以下元注解多用于自定义注解
 * ④ jdk提供的4种元注解: Retention;Target;Documented;Inherited
 *    元注解定义: 对现有的注解进行解释说明的注解
 *      @Target({可以用此注解修饰的程序元素集})
 *      @Retention(RetentionPolicy.XXX)
 *      @interface Annotation{}
 *   ① Retention: 指定所修饰的Annotation的生命周期:
        RetentionPolicy.SOURCE: 编译时discard
        RetentionPolicy.CLASS(默认行为): 编译时保留，执行时不加载
        RetentionPolicy.RUNTIME: 一直加载到内存中，反射读取
        只有声明为RUNTIME生命周期的注解，才能通过反射获取
 *   ② Target: 用于指定被修饰的Annotation能用于修饰哪些程序元素
 *      @Target({可以修饰的程序结构集合})
 *   // 以下出现频率较低
 *   ③ Documented: 表示所修饰的注解在被javadoc解析时保留下来(例deprecated)
 *   ④ Inherited: 被它修饰的Annotation将具有继承性
 *
 *
 * jdk8中注解的新特性: 可重复注解、类型注解
 * 1.可重复注解:
 *   jdk8之前的写法:
 *   @MyAnnotations({@MyAnnotation(value="hi"), @MyAnnotation(value="hi")})
 * ① 在MyAnnotation上声明@Repeatable，成员值为MyAnnotations.class
 * ② MyAnnotation的Target和Retention等元注解与MyAnnotations相同
 *
 * 2类型注解:
 * ElementType.TYPE_PARAMETER: 表示该注解能写在类型变量的声明语句中(如:泛型声明)
 * ElementType.TYPE_USE: 表示该注解能写在使用类型的任何语句中
 */


public class L_Annotation {
}


