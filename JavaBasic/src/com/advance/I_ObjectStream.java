package com.advance;

import org.junit.Test;

import java.io.*;

/**
 * @Description: ObjectStream，对象序列化机制，transient关键字
 * @create: 2022/01/01 - 22:43
 */

/*
 * 对象流的使用
 * ① ObjectInputStream 和 ObjectOutputStream
 * ② 作用: 用于存储和读取基本数据类型数据或对象的处理流。它的强大之处就是
 *    可以把Java中的对象写入到数据源中，也能把对象从数据源中还原回来。
 *
 * ③ 可序列化条件:
 *    Ⅰ.存储的对象必须是可序列化的 (满足Serializable / Externalizable接口);
 *       (Serializable接口无任何抽样方法，是标识接口)
 *    Ⅱ.当前类提供一个全局常量: static final long serialVersionUID;
 *    Ⅲ.除了当前类需要实现Serializable接口之外，还必须保证其内部所有属性
 *       也必须是可序列化的 (默认情况下，基本数据类型可序列化);
 *    Ⅳ.ObjectOutput/InputStream不可序列化static/transient修饰的成员变量;
 *       (transient表示不打算进行序列化标签的属性)
 *
 * ④ 序列化机制:
 *    对象序列化机制允许把内存中的Java对象转换成平台无关的二进制流，从而允许
 *    把这种二进制流持久地保存在磁盘上，或通过网络将这种二进制流传输到另一个
 *    网络节点; 当其它程序获取了这种二进制流，就可以恢复成原来的Java对象。
 *    > 序列化: 用ObjectOutputStream类<保存>基本类型数据或对象的机制
 *    > 反序列化: 用ObjectInputStream类<读取>基本类型数据或对象的机制
 * */

public class I_ObjectStream {
    /*
     * 序列化过程: 将内存中的java对象保存到磁盘中或通过网络传输出去;
     *            使用ObjectOutputStream实现
     **/
    @Test
    public void testObjectOutputStream() {
        // 序列化过程存储Object
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("object.dat"));
            // 传入object
            oos.writeObject(new String("你好"));
            oos.flush(); // 刷新
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert oos != null;
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * 反序列化过程: 将磁盘文件中的对象还原为内存中的一个java对象;
     *              使用ObjectInputStream来实现 ObjectInputStream
     * */
    @Test
    public void testObjectInputStream() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("objet.dat"));
            // 读取Object
            Object obj = ois.readObject();
            // 由于知道Object类型是String，可以强转为String
            String str = (String) obj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                assert ois != null;
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * @Description: 创建可序列化的类，实现Serializable接口
 **/
/*
 * 可序列化类条件:
 * Ⅰ.存储的对象必须是可序列化的 (满足Serializable接口或Externalizable接口);
 * Ⅱ.当前类提供一个全局常量: serialVersionUID;
 * Ⅲ.除了当前类需要实现Serializable接口之外，还必须保证其内部所有属性
 *    也必须是可序列化的 (默认情况下，基本数据类型可序列化);
 * Ⅳ.ObjectOutput/InputStream不可序列化static/transient修饰的成员变量
 **/
class Serial implements Serializable {
    // 提供独特serialVersionUID常量
    @java.io.Serial
    private static final long serialVersionUID = 31232L;
    String name;
    int age;
}
