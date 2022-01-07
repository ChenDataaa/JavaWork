package com.fundation;

import java.util.Arrays;

/**
 * @content: Java面向对象
 * @create 2021/12/18 - 12:15
 */
/*
 * Java是面向对象的语言，所有功能结构都封装在类中，通过类的实例化来调用具体功能结构
 * Java与前端和数据库交互时都体现为类和对象
 * 1.Java类与类的成员：属性，方法，构造器，(代码块，内部类)
 * 2.面向对象三大特征：封装，继承，多态，(抽象)
 * 3.其他关键字：this,super,static,final,abstract,interface,enum,package,import
 */
public class D_OOP {
    public static void main(String[] args) {
        // 类class和对象object，对象是类的个体instance实例
        // 类是抽象概念，对象是类的实体，设计类等同于设计类的成员
        // * 类的成员：[属性，方法]，(构造器，(代码块，内部类))
        // * 属性 = 成员变量 = field = 域
        // * 方法 = 成员方法 = method = 函数

        // # 1.1创建Person类的对象 (类的实例化)
        Person p1 = new Person();
        // # 1.2调用对象的结构 (属性和方法)
        // 调用属性
        // 可以接受其他类的对象为属性
        // 如果没有对属性赋值则调用默认值
        System.out.println(p1.name); // null
        p1.name = "张三";
        p1.isMale = true;
        System.out.println(p1.getName());
        System.out.println(p1.name); // 张三
        // 调用方法
        p1.sleep();
        p1.talk("汉语");

        // * 如果令p2 = p1，修改p2值也会影响p1值（类似数组指针快捷方式）
        // Person p2 = p1; // 导致p2和p1指向堆空间同一对象实体
        // p2.name = "李四"; // 此时p1.name也会改变
        // System.out.println("p1姓名: " + p1.name + " p2姓名: " + p2.name);

        // # 2.属性(成员变量) vs 局部变量
        // * 1.相同点
        // 1.1 定义变量格式一样，先声明后使用
        // 1.2 变量都有其作用域
        // * 2.不同点
        // 2.1 在类中声明的位置不同，属性在类{}内，局部变量在方法和形参中
        // 2.2 声明属性时可以指明权限 (private,protected,public,缺省)
        // 2.3 类的属性可以有初始化值 (同数组)，局部变量未赋值不可调用
        // 2.4 在内存加载位置不同，属性在堆(非static)，局部变量在栈

        // # 3.方法 (描述类中的具有的功能)，例如Math类中的数学运算方法
        // * 权限修饰符 [修饰方法] 返回值类型 方法名(形参列表) {方法体}
        // * 3.1 权限修饰符：private,protected,public,缺省 (封装时细说)
        // * 3.2 有无返回值
        // 3.2.1 如有返回值必须在方法声明时指定返回值类型，且return返回值
        // 3.2.2 没有返回值，声明用void表示，最后可以用return;来结束方法
        // * 3.3 类中的方法可以调用当前类中的属性和方法
        // * 3.4 不可以在方法里定义新的方法

        // # 4.匿名对象 (new 类().属性/方法)
        // 不显式赋值对象一个变量名，匿名对象只能调用一次
        // 每次匿名对象访问都是创建新的对象，彼此无关系
        // 适用于类的形参是另一个类的属性/方法时，可以将形参匿名调用
        // 匿名对象在开发中往往作为实参传递给形参:
        // class1 c1 = new class1 c1;
        // c1.method(new class2());

        // # 5.类方法method细谈
        // # 5.1 方法重载(method overload): 在同一个类中允许存在同名方法
        // 只与参数有关，需要参数个数/类型/顺序不同 (与返回类型无关)

        // # 5.2 可变个数形参
        // 5.2.1 与0或多个实参相匹配的形参，当调用可变形参方法时传入0-n个形参个数
        // 5.2.2 ! 可变个数形参不可以与同类型的数组共存 (同变量名)
        // 5.2.3 method(String[] strs)不可以与method(String ... strs)共存
        // 5.2.4 可变形参变量与数组操作方式相同
        // 5.2.5 可变个数形参在形参中最多只能有一个，且声明在末尾
        // * 格式: 数据类型 ... 变量名
        new Varargs().show(32);
        new Varargs().show("good");
        new Varargs().show("AA", "BB", "CC");
        new Varargs().show(new String[]{"DD"}); // 相当于...ss方式调用数组

        // # 5.3 方法参数的值传递机制
        // * 形参: 方法定义时，声明小括号内参数
        // * 实参: 方法调用时，实际传递给形参的值 (实际括号内给的变量值)
        // 方法形参的值传递机制:
        // * 如果数据是基本数据类型，此时赋值的是变量所保存的数据值
        // * 如果数据是引用数据类型，此时赋值的是变量所保存的地址值
        // 如果形参是基本数据类型则不会影响到实参，如果是引用数据类型会影响
        // 例如要定义swap交换数组中i,j元素位置
        // swap(int i, int j); // 错误
        // swap(int[] arr, int i, int j); // 正确

        // # 6.封装和隐藏 (权限修饰符public,private,protected,缺省)
        // 高内聚: 类的内部数据操作细节自己完成不允许外部干涉
        // 低耦合: 仅对外暴露少量的方法用于使用
        /*
         * 隐藏对象内部复杂性，只对外公开简单的接口便于外部调用，
         * 从而提高系统可拓展性和可维护性 (该隐藏隐藏，该暴露暴露)
         */
        // * 6.1 属性封装性private field:
        // 封装属性可以通过方法对属性赋值做额外的限制条件
        // 被封装的属性无法从外部直接赋值且无法直接调用
        /*
         * 封装性体现: private将属性私有化，
         * 同时提供public的方法get获取和set设置属性值
         */
        // 拓展体现: ①封装属性 ②封装方法仅供类内部使用 ③单例模式...
        // 例如animal类中让legs属性private，仅开放setLegs()方法赋值属性
        Animals dog = new Animals();
        dog.name = "Bao";
        // dog.legs = 4; // exist, but invisible
        // System.out.println(dog.legs);
        dog.setLegs(4);
        System.out.println(dog.getLegs());

        // * 6.2 封装性的体现需要权限修饰符来配合
        // * Java四种权限(从小到大): private->缺省->protected->public
        // protected在不同包的子类中也可使用
        /*
         修饰符     类内部   同一个包  不同包的子类  同一工程
         private    √
         (default)  √         √
         protected  √         √         √
         public     √         √         √          √
        */
        // * 修饰类只能用缺省和public

        // # 7.构造器/构造方法(constructor): 类的第三个成员
        // 构造器作用:
        // 7.1 创建类的对象: new + 构造器
        // 7.2 创建对象同时对属性做初始化
        // 7.3 可以通过构造器对属性设置默认初始值
        // 一个类中的多个构造器构成重载，彼此形参个数或类型不同(同方法类似)
        // 如果没有显式定义类的构造器，则系统默认提供一个空参构造器
        // 一旦定义了构造器，则new不会默认提供空参构造器
        // 例如public Person(int n); 此时想要new Person();会报错
        // * 构造器格式:
        // 权限符 类名(形参表){}
        // 7.4 JavaBean: Java可重用组件
        // 特点: ①类是公共的 ②有一个无参公共构造器 ③有属性有get，set方法

        // # 8.this关键字
        // this可以修饰属性，方法，构造器，this可以理解为当前对象或正在创建的对象
        // 在类的方法中可以使用this.属性或this.方法调用当前对象属性和方法(通常省略)
        // 例如当方法形参和属性重名时，用this.变量表明此变量为当前对象的属性而非形参
        // 也可以直接使用this(形参列表)在构造器中调用类中其他构造器(省去重复的代码)
        // 使用this()调用其他构造器时必须声明在当前构造器首行，且最多只有一个

        // # 9.package & import
        // package为了更好的进行项目管理，提供了包的概念，声明在源文件的首行
        // package包名要全小写(aaabbbccc)，每"."一次代表一层文件目录
        // 同一个包下不能命名同名接口，类
        // 调用包中的所有类import xxx.* (但不包括子包)
        // 如果使用的类在本包下则不用import，在别的包下则import package.class
        // 如果类名重名则不能import，要使用全类名package.class名来调用、
        // import static xxx.* 导入类中的static静态结构，例: 使用round()

        // # 10.继承
        // 不同类中某些方法/属性相同，可以继承使用相同功能
        // 减少代码冗余，提高复用性，便于功能的拓展，为之后多态性提供前提
        // class A extends B // A(subclass)继承B(superclass)的所有属性和功能
        // 通常定义一个父类包含了多个子类中共有的元素，子类获取父类结构属性和方法
        // 父类必须要有一个空参构造器public Parent();
        // Java单继承性: 一个父类可以有多个子类但一个子类只能继承一个父类
        // 子类继承父类功能后还可以声明自己特有的属性和方法实现功能的拓展
        // 继承可以有多层继承传递继承性，(直接父类和间接父类)
        // 如果没有声明类的父类，则此类默认继承于java.lang.Object类
        // 所有类都直接或间接继承于java.lang.Object类

        // # 11.方法重写(override/overwrite)
        // 对父类继承的方法进行改造重置，子类方法覆盖父类方法
        // 重写: 子类继承父类后可对父类同方法名且同形参的方法进行覆盖
        // * 11.1 修饰符:
        // * 子类重写方法的权限修饰符要不小于父类被重写方法 (父缺省，子public)
        // 特殊情况: 子类不能重写父类声明private权限的方法
        // * 11.2 返回值类型:
        // void/基本数据类型: 父类被重写返回值类型二者，子类重写返回值类型必须和父类相同
        // 引用数据类型: 父类被重写返回值类型是A类型，子类重写返回值类型可以是A类或A类子类
        // throws异常类型: 子类重写方法抛出的异常类型要不大于父类被重写异常类型
        // * 11.3 static和非static声明
        // 子类同名同参方法要么都非static(考虑重写)，要么都是static(不是重写)

        // # 12.super关键字
        // 使用super可以在子类中使用被重写覆盖的的父类同名方法
        // super可以修饰构造器，属性，方法
        // 显式调用父类同名同类型属性或方法: super.属性，super.方法
        // this先在本类中找，super先在父类中找
        // super调用构造器:
        // 在子类构造器中使用首行为super(形参)的方式调用父类生命的指定构造器
        // 在类的构造器中this()/super()只能选一个
        // * 如果子类构造器没有super()/this()，默认首行是super(空)
        // 因此父类必须要有一个空参构造器
        // Parent(int a, String b);
        // Children(int a, String b, double c){Super(a,b); this.c = c};

        // # 13.多态性，又叫向上转型(polymorphism)
        // 对象的多态性: 父类的引用指向子类的对象 (父类声明子类对象)
        /*
         * 13.1 多态性使用格式:
         * Parent 变量名 = new Child(); // Parent类是Child类的父类
         * 当调用子父类同名方法时，执行的是子类重写父类的方法 (虚拟方法调用)
         * 由于定义的是Parent型，只能调用父类的方法不可以调用子类特有的方法
         * 13.2 虚拟方法调用(动态绑定): 在编译期只能调用父类中声明的方法，
         * 在执行期执行的是子类重写父类的方法 (编译看左边，运行看右边)
         * 13.3 多态性使用前提:
         * 类是继承关系，子类要有方法的重写 (否则直接new Parent就好了)
         * ! 多态性只适用于方法，不适用于属性，调用的属性是父类的属性
         * 举例: 连接数据库的形参多态性可以连接多种数据库类型
         * 抽象类和接口的使用体现了多态性(由于不能实例化)

         * * 13.4 向下转型 (类型提升):
         * 多态性new在内存中实际上加载了子类特有的属性和方法，
         * 但是由于变量声明为父类类型，导致编译时只能调用父类属性和方法
         * 如果在多态性后要调用子类特有的属性方法，要提升类型
         * Parent p = new Child; Child c = (Child) p;
         * 但强制转型有风险ClassCastException异常，可能会转错到其他子类类型
         * 为了避免多态强转时异常，在向下转型前使用instanceof判断对象是否属于此类
         * 使用instanceof判断对象a是否时类A的实例，如果是返回true
         * 如果A类是B类父类，a instanceof B为true，则对其父类A也为true
         */

        // # 14.Object类
        // Object类作为所有类的父类，所有类可以使用Object类的方法
        // clone()，复制一个对象，(很少使用)
        // finalize()，如果对象在堆空间没有被引用，被调用垃圾回收 (回收前自动被调用)
        /*
         * 重点题: "==" 和 equals区别:
         * "=="是运算符，equals是方法
         * 1. "=="运算符:
         * -> "=="比较基本数据类型 (比较数值是否相等)，不一定类型要相等
         *    例: double 10.0 == int 10 返回true
         * -> "=="比较引用数据类型 (比较对象地址值是否相同)，即比较两个引用(变量名)
         *    是否指向同一个对象实体
         *    例: String s1 = new String("ab"); String s2 = new String("ab");
         *    仍然返回false，两String对象地址值不一样
         * -> "=="两边变量类型要一致(基本数据类型不一定相同，可以放宽)
         *
         * 2. equals方法使用:
         * -> equals是方法，无法使用在基本数据类型，只适用于引用数据类型
         * -> 在Object中的equals和"=="作用相同，都是比较对象地址是否相同，
         *    但String/Date/File/包装类等中的equals重写了Object类中的equals方法，
         *    比较的不再是两对象地址值，而是两对象的实体内容(属性)是否相等
         */

        /* 自定义类equals方法重写Object类equals，比较两对象实体内容而非对象地址
         * 先比较地址，如果地址相同直接返回true: if(this == obj): return true
         * 在比较对象是否是一个类下的: if(obj instanceof Class)
         * 比较属性前要对obj做向下转型Class x = (Class) obj，来获取真实obj属性和方法
         * 再比较两对象的属性等是否相同 {if (this.属性==x.属性)...}
         */

        // Object类中toString方法使用
        // 当输出对象的引用(变量名)时，实际是输出对象的toString()方法，也就是地址值
        // 如果没有重写，对象引用的toString()方法输出: 类的类名 + @ + hashcode
        // String/Date/File/包装类等都重写了toString()方法，使toString()返回实体内容信息

        // 15.JUnit单元测试
        // ① 创建Java类进行单元测试，此类是public，且提供公共无参构造器
        // ② 在此类中声明单元测试方法，方法权限public，无返回值无形参
        // ③ 单元测试方法上要声明@Test注解，并在单元测试类中import org.junit.Test
        // ④ 执行测试，如果执行结果没有异常则是绿色
        // 在方法外可以定义属性和方法，且在方法中可以直接调用
        // 可以建立多个@Test，选择某一个方法测试

        // # 16.包装类Wrapper的使用
        // Java提供了八种基本数据类型的包装类，使基本数据类型具有类的特征
        // int->Integer; Char->Character, 其他类都为首字母大写
        // 所有数值型类的父类是Number类
        // ① 基本数据类型，包装类，String类间的转换
        //    int i = 10; Integer j = i; String str = j.toString();
        // -> Integer parseInt & valueOf
        // -> String转基本/包装类: Xxx.parseXxx(str);
        //    int i = Integer.parseInt("123") 返回Int类型数字
        //    Integer i = Integer.valueOf("132") 返回Integer封装的对象
        // -> Boolean类忽略大小写，可以valueOf("TRUE")，如果是其他文字返回false
        // -> 包装类是类的对象，不能进行运算，需要转到基本数据类型做运算
        //    int i = j.intValue(); // j是Integer包装类对象
        // -> 基本数据类型转String: String str = num + "";
        //    基本/包装类转: String.valueOf(num);
        // ② 自动装箱自动拆箱
        // -> 自动装箱(基本数据类型-->包装类): Integer i = 10;
        //    Integer类内部定义了IntegerCache缓存结构，定义了Integer[]，
        //    保存了[-127,128]范围的整数，如果使用自动装箱的方式给Integer赋值，
        //    则可以直接使用数组中的元素，不用再去new，提高赋值效率
        //    如果自动装箱的两Integer类型同数值且范围在[-127,128]间，== 返回true
        //    从而方法中形参要求obj类的也可以使用基本数据类型自动装箱
        // -> 自动拆箱(包装类-->基本数据类型): int j = i; (i是包装类)
        /* 例题: Object o = true ? 1 : 2.0; // true ? new Integer(1) : new Double(2.0)
         * System.out.println(o) // 返回1.0，三元运算符统一类型提升
         */

        // # 17.static静态关键字
        /*
         * 随着类的加载而加载，早于对象的创建，让某些特定数据在内存空间里只有一份，
         * 存在于内存的方法区的静态域中
         */
        // static用来修饰属性/方法/代码块/内部类 (不可修饰构造器)
        // ① 使用static修饰属性: 静态变量/属性(类变量) vs 非静态属性(实例变量)
        //    对static属性赋值，所有对象此属值都相同，内存中只保留一份此属性
        // -> 实例变量: 创建类的多个对象，每个对象都独立拥有一套类中的非静态属性，
        //             当修改其中一个对象的非静态属性不会影响其他对象同属性
        // -> 静态变量: 创建类的多个对象共享同一个静态变量，修改其中一个对象的静态变量，
        //             其他对象的此属性也会被修改
        // -> 实例变量通过创建新的对象而加载，静态变量随着类加载而加载(类加载早于对象)
        //    因此可以在类的实例化(创建类对象)之前使用"类.静态变量"的方式调用静态变量
        //    由于类在内存中只会加载一次，静态变量在内存中也只存在一份(在方法区的静态域中)
        //    静态属性举例: System.out; Math.PI
        // ② 使用static修饰方法: 静态方法
        //    静态方法随着类的加载而加载，可以通过"类.静态方法"进行调用
        //    静态方法中只能调用静态方法或属性，非静态方法都可以调用、
        /*
         * 注意: 在静态方法中不能使用this和super关键字(this和super都是基于对象的)
         * 方法中调用静态方法省略的是"类名.静态方法"，而非"this"，
         * 在父类子类同名同参数的static方法不认为是方法重写
         */
        // ③ 开发中如何确定属性和方法是否声明为static:
        // -> 属性是可以被多个对象所共享的，不会随对象不同而不同的(利率，计算total对象数)
        //    类中的常量final一般也常常声明为static
        // -> 操作静态属性的方法通常也设置为静态方法
        // -> 工具类的方法通常设置为static，没有必要造对象(Math,Arrays,Collections)

        // # 18.Singleton设计模式
        // ④ 单例设计模式(Singleton): 关于static的设计模式 (Runtime类)
        //    采取一定的方法保证在整个软件系统中，对某个类只能存在一个对象实例
        //    对某个类只能存在一个对象实例，并且该类只提供一个取得其对象实例的方法
        // -> 构造器的访问权限设置为private，使得类外部不能造对象
        /*
         * class Bank {
         *    // 2.内部创建类的对象(也可以理解为类的属性)，要求此对象为静态
         *    private static Bank instance = new Bank();
         *    // 1.私有化类的构造器，避免在Bank类外造对象
         *    private Bank(){}
         *    // 3.提供公共的静态方法，返回类的对象，提供静态方法可以不用造对象外部调用
         *     此方法
         *    public static Bank getInstance(){
         *        return instance;
         *       }
         * }
         */
        // 区分单例设计模式饿汉式和懒汉式:
        // 饿汉式: 坏: 对象加载时间长; 好: 线程安全
        // 懒汉式: 好: 延迟对象的创建; 坏: (目前)线程不安全，多线程时修改
        // * 应用场景: 网站计数器，应用程序日志，数据库连接池，读取配置文件的类

        // 19.main()方法的使用说明
        // main()方法作为程序的入口，main()也是个普通的静态方法，可以通过类调用
        // main()方法也可以作为与控制台交互的方式 (在arguments里面添加参数)

        // # 20.Block代码块 (初始化块)
        // ① 作用: 用来初始化类，对象
        // ② 代码块内可以有输出语句，代码块无法被调用，通常是自动执行
        // ② 代码分为"static{}"和"{}"静态和非静态代码块
        // -> 静态代码块: 随着类的加载而执行(优先于非静态代码块执行)，且只执行一次
        //      静态代码块只能调用静态属性和方法
        //      作用: 对类的属性初始化，可以通过"类.属性"调用属性
        // -> 非静态代码块: 随着new创建对象时执行，且每创建一次执行一次
        //      可以调用静态和非静态属性和方法
        //      作用: 可以在创建对象时，对对象属性初始化

        // # 21.final关键字
        // final可以修饰: 类，方法，变量
        /*
         * 修饰类: 表示此类无法被继承，例String,System,StringBuffer类，表示类不需要再拓宽功能
         * 修饰方法: 方法不能被重写，方法不需要扩充，例如Object类中的getClass()
         * 修饰变量: 此时变量变为常量，
         *          ① 修饰属性: 可以在显式初始化，代码块，构造器，不可在方法中赋值
         *          ② 修饰局部变量(方法内变量): final修饰形参时表示形参是常量
         *
         * 通常static final一起修饰属性表示全局常量
         */

        // # 22.abstract抽象类与抽象方法
        // abstract关键字可以修饰类和方法
        // ① abstract修饰类:
        //    类不能实例化(不能创建类的对象)
        //    但仍有构造器(作为super()继承给子类)，子类对象实例化时调用
        //    抽象类不能实例化，因此都会提供抽象类的子类，让子类对象实例化完成操作
        // ② abstract修饰方法:
        //    抽象方法一定存在于抽象类中，抽象类不一定有抽象方法
        //    只有方法声明，没有方法体(没有方法的{}): public abstract void method();
        //    如果抽象类中存在抽象方法，子类必须重写此抽象方法(除非子类也是抽象类)
        //    只有子类重写了抽象父类的所有抽象方法后才可实例化
        //    如子类没有重写抽象父类的所有抽象方法，则此子类也是抽象类，不能实例化
        // ③ abstract使用注意点:
        //    abstract不能修饰属性，构造器(可以定义不能修饰)，代码块
        //    abstract不能修饰private,static(同名同参static方法不叫重写),final方法

        // # 23.抽象类的匿名子类
        // ① Class a = new Class(); // 非匿名类非匿名对象
        // ② new Class(); // 非匿名类匿名对象
        // ③ abstract Class p = new abstract Class(){@Override}; // 匿名类
        // ④ new abstract Class(){@Override} // 匿名类匿名对象
        // ⑤ 多态和抽象类的应用: TemplateMethod模板方法设计模式
        //    功能一部分确定一部分不确定，可以把不确定的部分暴露让子类去实现(重写抽象方法)
        //    整体步骤固定，在父类中写好，但某些部分易变，易变部分可抽象供不同子类实现

        // 24.interface接口
        // ① 接口使用interface定义
        // ② 在Java中接口和类是并列结构
        // ③ 定义接口和定义接口成员:
        //    JDK7以前:
        //    ->只能定义全局常量(public static final) 属性类型 属性名;
        //    ->和抽象方法(public abstract) 返回值类型 方法名();
        //    JDK8之后:
        //    ->加入静态方法: (public) static 返回值类型 方法名(){};
        //      接口中的静态方法只能由接口调用，实现类无法调用(类似于工具类)
        //    ->加入默认方法: (public) default 返回值类型 方法名(){};
        //      实现类的对象可以调用接口中的默认方法，接口名.不可调用
        //      默认方法可以被实现类重写，调用使用"接口名.super.类名()"
        //      如果子类(实现类)同时继承的父类和实现的接口声明了同名同参方法，
        //      那么在子类没有重写此方法的前提下，默认调用父类的方法(类优先原则)
        // ④ 接口中不能定义构造器！意味着接口不可实例化(super不会起作用)
        // ⑤ Java开发中，接口通过让类去实现接口: class A implements B{}
        //    如果此实现类重写覆盖了所有接口中的抽象方法，则此实现类可实例化
        //    如果实现类没有实现完接口中的所有抽象方法，则此实现类仍为抽象类
        // ⑥ Java类可以实现多个接口(多个接口用逗号分隔)->弥补了Java单继承的局限性
        //    类实例化多个接口需要实现多个接口的所有抽象方法
        //    格式: class A extends B implements C, D...{}; 类A继承类B实现多个接口
        // ⑦ 接口与接口之间可以继承，而且可以多继承
        //    格式: interface A extends B, C...{}; 其中A,B,C都是接口
        // ⑧ 接口的具体使用体现了多态性
        // ⑨ 接口本质是标准和规范，体现了"如果你是/要...则必须能..."
        //    继承体现了"是不是"，接口体现了"能不能"，开发中体会面向接口编程
        //    接口的设计模式应用: Proxy代理模式，Factory工厂设计模式
        /* 抽象类和接口共同点和区别:
         * 都不能实例化; 都可以定义抽象方法; JDK8以后接口更像抽象类
         * 抽象类有构造器，接口没有; 接口可以多继承(实现)
         * */

        // 25.内部类(出现很少)
        // 将一个类声明在另一个类中，则此类为内部类
        // 内部类分类: 成员内部类(静态,非静态) & 局部内部类(方法内,代码块内,构造器内)
        // 25.1 成员内部类:
        // ① 可以定义属性方法构造器
        // ② 可以被final修饰，表示此类不可被继承; 可以被abstract修饰，不可实例化
        // ③ 可以调用外部类的方法和属性(注意静态调静态)，调用方式"外部类名.this.属性"
        // ④ 可以被static修饰和四种权限修饰符修饰(public...)
        // ⑤ 外部声明static静态成员内部类: new ClassA.ClassB()
        //    非静态成员内部类要先创建外部类的实例:
        //       ClassA p = new ClassA(); ClassA.ClassB c = p.new ClassB()
        // 25.2 局部内部类:
        // 通常用于返回一个实现了接口类的对象
    }
}

// 创建类
class Person {
    // 属性 field (成员变量)
    String name;
    boolean isMale;

    // 定义构造器
    public Person() { // 空参构造器
    }

    public Person(String name) {
        // 调用其他构造器时必须声明在当前构造器首行，且构造器内部最多声明一个其他构造器
        this(); // 相当于先创建一个空的结构器，随后补充额外代码
        this.name = name; // this关键字
    }

    public Person(String name, boolean isMale) {
        // 将name传输到Person(String name)结构器中
        this(name);
        // 然后再执行下面代码
        this.isMale = isMale;
    }

    // 方法 method (描述类中具有的功能)
    public void sleep() {
        System.out.println(name + "在睡觉");
    }

    // 可以通过方法调用属性，返回String类型数据
    public String getName() {
        return name;
    }

    public void talk(String language) { // language是形参
        System.out.println("语言是: " + language);
    }
}

// * 方法重载(method overload) & 可变个数形参 ...
class Varargs {
    public void show(int i) {
        System.out.println(i);
    }

    // 只输入一个字符串，以下show省去也不会报错
    public void show(String s) {
        System.out.println(s);
    }

    // * 与数组操作方法一样
    public void show(String... ss) {
        System.out.println(Arrays.toString(ss));
    }
}

class Animals {
    String name;
    // 令legs属性对外封装
    private int legs;

    // 对封装属性的获取
    public int getLegs() {
        return legs;
    }

    public void setLegs(int leg_num) {
        // 此时属性对外部不可见
        if (leg_num >= 0 && leg_num <= 100) {
            legs = leg_num;
        } else {
            legs = 0;
        }
        System.out.println("name: " + name + ", legs: " + legs);
    }
}
