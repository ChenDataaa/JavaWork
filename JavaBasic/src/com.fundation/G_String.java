package com.fundation;

import org.junit.Test;

/**
 * @Description: Java常用类: String,StringBuffer,StringBuilder
 * @create 2021/12/27 - 16:27
 */

public class G_String {
    @Test
    public void StringTest() {
/*
 *  ① String是final类，不可继承，只有String可以不用new直接创建对象
 *  ② String实现了Serializable接口: 表示字符串是支持序列化的
 *  ③ String实现了Comparable接口: 表示String可以比较大小
 *  ④ String内部定义了final char[] value用于存储字符串数据，代表不可变的字符序列
       字符串不可变性体现:
       ->当对字符串重新赋值时，需要重写指定内存区域赋值，不能使用原有的value进行赋值
       ->当对现有的字符串进行连接操作时，也需要重新指定内存区域赋值，不能使用原有的value
       进行赋值String a = "abc"; b = a; a += "def"; // a = abcdef; b = abc;
       ->当调用String的replace()方法修改指定字符或字符串时，也要重新指定内存区域赋值，
       不能使用原有的value进行赋值
 *  ⑤ 通过字面量的方式 (非new) 给一个字符串赋值，此时的字符串值声明在字符串常量池中，
       String a = "abc"; String b = "abc"; a,b在字符串常量池中地址相同。
       (字符串常量池中是不会存储相同内容的字符串的)
 *  ⑥ String str1 = "abc"; String str2 = new String("abc")区别:
 *     str1声明的字符串地址在方法区的常量池中，str2指向对象在堆空间中地址，
 *     堆空间的存放的是指向常量池字符串的地址
 *
 *  面试题:
 *  String s = new String("abc");方式创建对象，在内存中创建了几个对象?
    两个: 一个是堆空间中new结构，另一个是char[]对应的常量池中的数据: "abc"

//        String s1 = "javaEE";
//        String s2 = "javaEE";
//        String s3 = new String("javaEE");
//        String s4 = new String("javaEE");
//        System.out.println(s1 == s2);// true
//        System.out.println(s1 == s3);// false
//        System.out.println(s1 == s4);// false
//        System.out.println(s3 == s4);// false
//
//        Person p1 = new Person("Tom", 12);
//        Person p2 = new Person("Tom", 12);
//        System.out.println(p1.name.equals(p2.name)); // true
//        System.out.println(p1.name == p2.name);      // true
 *
 *
 *  面试题字符串内存理解:
 *  ① 常量与常量的拼接结果在常量池。且常量池中不会存在相同内容的常量
 *  ② 只要其中有一个是变量，结果就在堆中
 *  ③ 如果拼接的结果调用intern()方法，返回值就在常量池中
 *  ④ 常量和常量的拼接在常量池中(final的字符串在常量池)
        String s1 = "javaEE";
        String s2 = "hadoop";
        String s3 = "javaEEhadoop";      // 常量池
        String s4 = "javaEE" + "hadoop"; // 常量池
        String s5 = s1 + "hadoop";       // 有变量名参与，在堆空间(new)，不在常量池
        String s6 = "javaEE" + s2;       // 有变量名参与，在堆空间(new)，不在常量池
      * String s7 = s5.intern();         // intern返回常量池中字符串地址
        System.out.println(s3 == s4); // true
      * System.out.println(s3 == s8); // true，使用了intern
        System.out.println(s3 == s5); // false
        System.out.println(s3 == s6); // false
        System.out.println(s5 == s6); // false
 *
 *  面试题: 方法中形参是String，将一个String对象传入此方法不会更改此对象的String内容:
 *
 */
        String p = "bad";
        new G_String().change(p);
        System.out.println(p); // bad

/*
 *  2.String的常用方法:
 *    int length(): 返回字符串的长度: return value.length()
 *    char charAt(int index): 返回某索引处的字符return value[index]
 *    boolean isEmpty(): 判断是否是空字符串 (return value.length == 0)
 *    String toLowerCase(): 将String中的所有字符转换为小写(String本身不变)
 *    String toUpperCase(): 将String中的所有字符转换为大写(String本身不变)
 *    String trim(): 返回字符串的副本，忽略前导空白和尾部空白
 *    boolean equals(Object obj): 比较字符串的内容是否相同
 *    boolean equalsIgnoreCase(String a): 与equals方法类似，忽略大小写
 *    String concat(String str): 将指定字符串连接到此字符串的结尾，等价于"+"
 *    int compareTo(String anotherString): 比较两个字符串的大小，如果结果为
         负数，表示前字符串小(abc < abz)，否则前字符串大
 *    String substring(int beginIndex): 返回一个新的字符串，
         它是此字符串的从beginIndex开始截取到最后的一个子字符串。
 *    String substring(int beginIndex, int endIndex): 返回一个新字符串，
         它是此字符串从beginIndex开始截取到endIndex(不包含)的一个子字符串。
 *    boolean endsWith(String suffix): 测试此字符串是否以指定的后缀结束
 *    boolean startsWith(String prefix): 测试此字符串是否以指定的前缀开始
 *    boolean startsWith(String prefix, int toffset): 测试此字符串从指定
         索引开始的子字符串是否以指定前缀开始
 *    boolean contains(CharSequence s): 字符串包含指定的char值序列时返回true
 *    int indexOf(String str): 返回指定子字符串在此字符串中第一次出现处的索引，
         未找到返回-1
 *    int indexOf(String str, int fromIndex): 从指定的索引fromIndex处开始，
         返回指定子字符串在此字符串中第一次出现处的索引
 *    int lastIndexOf(String str): 返回指定子字符串在此字符串最后出现处的索引
                 (仍是首字母开头处的索引)
 *    int lastIndexOf(String str, int fromIndex): 从指定的索引开始反向搜索，
         返回指定子字符串在此字符串中最后一次出现处的索引
 ---- -------------------------------------------------------------------
 *    字符串替换:
 *    String replace(char oldChar, char newChar): 返回一个新的字符串，
         它是通过用 newChar 替换此字符串中出现的所有 oldChar 得到的。
 *    String replace(CharSequence target, CharSequence replacement):
         使用指定的字面值替换序列替换此字符串所有匹配字面值目标序列的子字符串。
 *    String replaceAll(String regex, String replacement):
         使用给定的 replacement 替换此字符串所有匹配给定的正则表达式的子字符串。
 *    String replaceFirst(String regex, String replacement):
         使用给定的 replacement 替换此字符串匹配给定的正则表达式的第一个子字符串。
 *    字符串匹配:
 *    boolean matches(String regex): 告知此字符串是否匹配给定的正则表达式。
 *    字符串切片:
 *    String[] split(String regex): 根据给定正则表达式的匹配拆分此字符串。
 *    String[] split(String regex, int limit): 根据匹配正则表达式来拆分字符串，
                 最多不超过limit个，如果超过了，剩下的全部都放到最后一个元素中。
 *    str.split("\\,")  // "\\"表示正则
 */

        /*
         *  3.String 与 char[]之间的转换
         *   String -> char[]: String.toCharArray()
         *   char[] -> String: 调用new String(char[])构造器
         */
        String ch = "abc123";
        char[] c1 = ch.toCharArray();
        new String(c1);

        /*
         *  4.String 与 byte[]之间的转换
         *  编码: 字符串 -> 字节 (看得懂 -> 看不懂的二进制数据)
               String -> byte[]: String.getBytes()，返回String的ASCII码数组
               如果要编写String汉字的byte数组就有问题(默认UTF-8)，需要转换编码集(gbk)
         *  解码: 编码的逆过程，字节 -> 字符串 (看不懂的二进制数据 -> 看得懂)
               byte[] -> String: 调用String的构造器: new String(byte[], 编码集)
         *  解码使用的字符集必须与编码时使用的字符集一致，否则会出现乱码。
         */
        byte[] bt = ch.getBytes();
    }

    public void change(String str) {
        str = "good";
    }

    @Test
    public void StringBufferTest() {
/*
 *  String, StringBuffer, StringBuilder三者的异同？
 *  String: 不可变的字符序列; 底层使用char[]存储
 *  StringBuffer: 可变字符序列; 线程安全，效率低; 底层使用char[]存储
 *  StringBuilder: 可变字符序列; jdk5.0新增，线程不安全，效率高; 底层使用char[]存储
 *  源码分析:
 *  String str = new String();       //char[] value = new char[0];
 *  String str1 = new String("abc"); //char[] value = new char[]{'a','b','c'};
 *  StringBuffer sb1 = new StringBuffer(); //char[] value = new char[16];
        //底层创建了一个长度是16的数组。
 *
 *  注意: 底层输出length返回的是字符串中字符count属性而非数组长度
 *  System.out.println(sb1.length()); //0
 *  sb1.append('a'); //value[0] = 'a';
 *  sb1.append('b'); //value[1] = 'b';
 *  StringBuffer sb2 = new StringBuffer("abc");
        //char[] value = new char["abc".length() + 16]; 底层默认增加16个位置

 *  扩容问题: 如果要添加的数据底层数组盛不下了，就需要扩容底层的数组。
             默认情况下，扩容为原来容量的2倍+2，同时将原有数组中的元素复制到新的数组中。
       开发中建议使用：StringBuffer(int capacity) 或 StringBuilder(int capacity)，
       避免数组容量不够扩容复制降低效率
*/

/*
 *  StringBuffer & StringBuilder的常用方法:
 *  StringBuffer append(xxx): 用于进行字符串拼接
 *  StringBuffer delete(int start,int end): 删除指定位置的内容
 *  StringBuffer replace(int start, int end, String str):
        把[start,end)位置替换为str字符串
 *  StringBuffer insert(int offset, xxx): 在指定位置插入xxx
 *  StringBuffer reverse(): 把当前字符序列逆转
 *  public int indexOf(String str)
 *  public String substring(int start,int end):
        返回一个从start开始到end索引结束的左闭右开区间的子字符串
 *  public void setCharAt(int n ,char ch)
 *
 *  增: append(xxx)
 *  删: delete(int start,int end)
 *  改: setCharAt(int n ,char ch)               //改一个
        replace(int start, int end, String str) //改多个
 *  查: charAt(int n)
 *  插: insert(int offset, xxx)
 *  长度: length()
 *  遍历: for() + charAt(); toString()
 */

        // StringBuffer & StringBuilder很多对字符串操作无返回值，直接在原有字符串上改变
        StringBuffer sb1 = new StringBuffer("abc");
        sb1.setCharAt(0, 'm');
        System.out.println(sb1);          // mbc
        System.out.println(sb1.length()); // 3
        sb1.append(12);
        sb1.append("hi");
        sb1.delete(0, 1);                 // 删除第一个字符
        System.out.println(sb1);          // bc12hi
    }
}
