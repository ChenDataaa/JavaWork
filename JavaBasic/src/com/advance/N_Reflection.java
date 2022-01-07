package com.advance;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/**
 * @Description: 反射
 * @create: 2022/01/03 - 0:23
 */

/*
  疑问1: 通过直接new的方式或反射的方式都可以调用公共的结构，开发中到底用那个？
     >建议: 直接new的方式。
     >什么时候会使用反射的方式: 反射的特征: 动态性
     >动态性体现: 运行时可以根据某些条件改变自身结构(在编译时无法确定创建某一类对象);
      (其中可以通过条件语句通过字符串方式使用ClassPath创建类对象)

  疑问2: 反射机制与面向对象中的封装性是不是矛盾的？如何看待两个技术？
  不矛盾。
 */

/*
 * 关于java.lang.Class类的理解
 * 1.类的加载过程:
 *   程序经过javac.exe命令以后，会生成一个或多个字节码文件(.class结尾); 接着使用
 *   java.exe命令对某个字节码文件进行解释运行; 相当于将某个字节码文件加载到内存中，
 *   此过程就称为类的加载。加载到内存中的类称为运行时类，此运行时类，就作为Class的
 *   一个实例; (Class clazz = Person.class)，Person类本身作为Class的一个实例;
 * 2.换句话说，Class的实例就对应着一个运行时类; (并不是new class)
 * 3.加载到内存中的运行时类，会缓存一定的时间。在此时间之内，可以通过不同的方式
 *   来获取此运行时类，地址值相同
 */
public class N_Reflection {
    /*
     * # 获取Class实例的方式: (前三种掌握)
     *
     * ① 调用运行时类的属性: .class
     *    Class<Pearson> clazz = Pearson.class;
     * ② 通过运行时类的对象: getClass()
     *    Pearson p1 = new Pearson();
     *    Class clazz = p1.getClass();
     * ③ 调用Class静态方法(用最多): forName(String classPath);
     *    (classPath类的包含包名在内的全路径名)
     *    Class.forName("com.advance.Pearson")
     * ④ 使用类的加载器(了解): ClassLoader
     *    ClassLoader cl = ReflectionTest.class.getClassLoader();
     *    Class clazz = cl.loadCLass("com.advance.Pearson");
     * */

    /**
     * @Description: 通过反射创建对应的运行时类的对象
     */
    @Test
    public void test1() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 带泛型的Class，创建Instance实例时自动识别为Pearson
        Class<Pearson> clazz = Pearson.class;
        /*
         * newInstance():调用此方法，创建对应的运行时类的对象。内部调用了运行时类的
         *               空参的构造器。
         *
         * 要想此方法正常创建运行时类的对象，要求:
         * 1.运行时类必须提供空参的构造器
         * 2.空参的构造器的访问权限得够。通常，设置为public
         *
         * 在javabean中要求提供一个public的空参构造器。原因:
         * 1.便于通过反射，创建运行时类的对象
         * 2.便于子类继承此运行时类时，默认调用super()时，保证父类有此构造器
         */
        // 用newInstance()创建运行时类的对象(反射对象)
        // 默认使用空参构造器，如不提供空参构造器则返回InstantiationException
        // newInstance()过时，使用getDeclaredConstructor调用指定构造器再调用newInstance()
        Pearson obj = clazz.getDeclaredConstructor().newInstance();
        System.out.println(obj);
    }

    /**
     * @Description: 反射调用属性: 权限修饰符, 数据类型, 变量名; 获取方法
     */
    @Test
    public void test2() {
        // 权限修饰符 数据类型 变量名
        Class clazz = Person.class;

        // 获取属性结构:
        // ① getFields(): 获取当前运行时类及其父类中声明为public访问权限的属性
        Field[] fields = clazz.getFields();
        for (Field f : fields) {
            System.out.println(f);
        }
        System.out.println();
        // ② getDeclaredFields(): 获取当前运行时类中声明的所有属性(全部权限)
        // (不包含父类中声明的属性)
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f : declaredFields) {
            // 1.权限修饰符
            int modifier = f.getModifiers();
            System.out.print(Modifier.toString(modifier) + "\t");

            // 2.数据类型
            Class type = f.getType();
            System.out.print(type.getName() + "\t");

            // 3.变量名
            String fName = f.getName();
            System.out.print(fName);
            System.out.println();
        }
    }

    /**
     * @Description: 反射调用方法
     * * @注解 权限修饰符 返回值类型 方法名(参数类型1 形参名1,...) throws
     */
    @Test
    public void test3() {
        Class clazz = Person.class;
        // 获取运行时类方法(类似属性): getMethods(); getDeclaredMethods()
        // getMethods(): 获取当前运行时类及其所有父类中声明为public权限的方法
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            System.out.println(m);
        }
        System.out.println();

        // getDeclaredMethods(): 获取当前运行时类中声明的所有方法(不包含父类方法)
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m : declaredMethods) {
            // 1.获取方法声明的注解
            Annotation[] annos = m.getAnnotations();
            for (Annotation a : annos) {
                System.out.println(a);
            }

            // 2.权限修饰符
            System.out.print(Modifier.toString(m.getModifiers()) + "\t");

            // 3.返回值类型
            System.out.print(m.getReturnType().getName() + "\t");

            // 4.方法名
            System.out.print(m.getName());
            System.out.print("(");

            // 5.形参列表
            Class[] parameterTypes = m.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                if (i == parameterTypes.length - 1) {
                    System.out.print(parameterTypes[i].getName() + " args_" + i);
                    break;
                }
                System.out.print(parameterTypes[i].getName() + " args_" + i + ",");
            }
            System.out.print(")");

            // 6.抛出的异常
            Class[] exceptionTypes = m.getExceptionTypes();
            if (exceptionTypes.length > 0) {
                System.out.print("throws ");
                for (int i = 0; i < exceptionTypes.length; i++) {
                    if (i == exceptionTypes.length - 1) {
                        System.out.print(exceptionTypes[i].getName());
                        break;
                    }
                    System.out.print(exceptionTypes[i].getName() + ",");
                }
            }
            System.out.println();
        }
    }

    /**
     * @Description: 获取构造器结构
     */
    @Test
    public void test4() {
        Class clazz = Pearson.class;
        // getConstructors():获取当前运行时类中声明为public的构造器
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor c : constructors) {
            System.out.println(c);
        }
        System.out.println();
        // getDeclaredConstructors():获取当前运行时类中声明的所有的构造器
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor c : declaredConstructors) {
            System.out.println(c);
        }
    }

    /**
     * @Description: 获取运行时类的父类, 获取运行时类的带泛型的父类
     */
    @Test
    public void test5() {
        Class clazz = Pearson.class;

        Class superclass = clazz.getSuperclass();
        System.out.println(superclass);
        // 获取带泛型的父类
        Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println(genericSuperclass);

        // 获取运行时类的带泛型的父类的泛型
        ParameterizedType paramType = (ParameterizedType) genericSuperclass;
        // 获取泛型类型
        Type[] actualTypeArguments = paramType.getActualTypeArguments();
        // System.out.println(actualTypeArguments[0].getTypeName());
        System.out.println(((Class) actualTypeArguments[0]).getName());
    }

    /**
     * @Description: 获取运行时类实现的接口
     */
    @Test
    public void test6() {
        Class clazz = Pearson.class;

        Class[] interfaces = clazz.getInterfaces();
        for (Class c : interfaces) {
            System.out.println(c);
        }

        System.out.println();
        // 获取运行时类的父类实现的接口
        Class[] interfaces1 = clazz.getSuperclass().getInterfaces();
        for (Class c : interfaces1) {
            System.out.println(c);
        }
    }

    /**
     * @Description: 获取运行时类所在的包
     */
    @Test
    public void test7() {
        Class clazz = Pearson.class;

        Package pack = clazz.getPackage();
        System.out.println(pack);
    }

    /**
     * @Description: 获取运行时类声明的注解
     */
    @Test
    public void test8() {
        Class clazz = Pearson.class;

        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annos : annotations) {
            System.out.println(annos);
        }
    }

    @Test
    public void test() throws Exception {
        Class<Pearson> clazz = Pearson.class;
        // 1.通过反射，创建Pearson类的对象
        Constructor<Pearson> cons = clazz.getConstructor(String.class, int.class);
        Object obj = cons.newInstance("Tom", 12);
        Pearson p = (Pearson) obj;
        System.out.println(p.toString());

        // 2.通过反射，调用对象指定的属性、方法
        // 调用属性
        Field age = clazz.getDeclaredField("age");
        age.set(p, 10);
        System.out.println(p.toString());

        // 调用方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(p);

        System.out.println("*******************************");

        // 通过反射，可以调用Pearson类的私有结构的。比如: 私有的构造器、方法、属性
        // 调用私有的构造器
        Constructor cons1 = clazz.getDeclaredConstructor(String.class);
        cons1.setAccessible(true);
        Pearson p1 = (Pearson) cons1.newInstance("Jerry");
        System.out.println(p1);

        // 调用私有的属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p1, "HanMeimei");
        System.out.println(p1);

        // 调用私有的方法
        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String nation = (String) showNation.invoke(p1, "中国");// 相当于String nation = p1.showNation("中国")
        System.out.println(nation);
    }
}


class Pearson {

    public int id;
    public int age;
    private String name;

    public Pearson(String name, int age) {

        this.name = name;
        this.age = age;
    }

    private Pearson(String name) {
        this.name = name;
    }

    public Pearson() {
        System.out.println("Pearson()");
    }

    @Override
    public String toString() {
        return "Pearson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void show(String str) {
        System.out.println("你好，" + str);
    }

    private String showNation(String nation) {
        System.out.println("我的国籍是: " + nation);
        return nation;
    }
}
