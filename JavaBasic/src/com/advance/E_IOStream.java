package com.advance;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


// 使用Ctrl+Alt+t: 选择环绕方式

/**
 * @Description: IOStream => FileReader/Writer; FileInputStream/OutputStream
 * @create: 2022/01/01 - 1:32
 */
/*
 * 一、流的分类:
 *  1.操作数据单位: 字节流、字符流
 *  2.数据的流向: 输入流、输出流
 *  3.流的角色: 节点流、处理流
 *  对于文本文件(.txt,.java,.c,.cpp)，使用字符流处理
 *  对于非文本文件(.jpg,.mp3,.mp4,.avi,.doc,.ppt,...)，使用字节流处理
 *
 *  抽象基类       字节流         字符流
 *  输入流      InputStream      Reader
 *  输出流      OutputStream     Writer
 *
 * 二、流的体系结构
 * 抽象基类         节点流(或文件流)(开发中用的少)
 * InputStream     FileInputStream (read(byte[] buffer))
 * OutputStream    FileOutputStream (write(byte[] buffer, 0, len)
 * Reader          FileReader (read(char[] cbuf))
 * Writer          FileWriter (write(char[] cbuf, 0, len)
 *
 * 缓冲流(处理流的一种)(开发中用的多)
 * BufferedInputStream (read(byte[] buffer))
 * BufferedOutputStream (write(byte[] buffer, 0, len) / flush()
 * BufferedReader (read(char[] cbuf) / readLine())
 * BufferedWriter (write(char[] cbuf, 0, len) / flush()
 * */
public class E_IOStream {
    /**
     * @Description: 读取字符文件数据FileReader
     */
    /*
     * FileReader读取文件数据时，要使用try-catch-finally，不可以选择throws，
     * 可能导致内存泄漏，读取的file无法关闭
     *
     * 过程步骤:
     *  ① File类的实例化
     *  ② FileReader流的实例化
     *  ③ 读入的操作read()
     *  ④ 资源的关闭close()
     *
     * 总结:
     *  1.read(): 返回读入的一个字符。如果达到文件末尾，返回-1;
     *  2.为了保证流资源一定可以执行关闭操作。需要使用try-catch-finally处理;
     *  3.读入的文件一定要存在，否则就会报FileNotFoundException.
     **/
    @Test
    public void testFileReader1() {
        FileReader fr = null;
        try {
            // 创建File类对象，在@Test中创建当前路径对象等同于在当前Module下
            File file = new File("hello.txt");
            // 提供具体流流
            fr = new FileReader(file);
            // 数据的读入
            // read(): 返回读入的一个字符; 如果达到文件末尾，返回-1
            int data;
            while ((data = fr.read()) != -1) {
                System.out.print((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //* 一定要关闭流
            try {
                assert fr != null;
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * 更改read()的操作，不再一个一个读取，而是使用char[] cbuf数组读取，每次
     * 读入多个字符数
     * */
    @Test
    public void testFileReader2() {
        FileReader fr = null;
        try {
            File file = new File("hello.txt");
            fr = new FileReader(file);
            // 每次读取5个字符
            char[] cbuf = new char[5];
            // fr.read(cbuf): 每次读入到数组中的个数，读取到末尾返回-1
            int len;
            while ((len = fr.read(cbuf)) != -1) {
                // 方式一 (正确):
                for (int i = 0; i < len; i++) {
                    System.out.print(cbuf[i]);
                }
                // 方式二 (错误写法):
                // 等同于for循环中使用 i < cbuf.length()
                // String str = new String(cbuf);
                // System.out.println(str); // 返回helloWorld123ld
                // 方式二 (正确写法):
                String str = new String(cbuf, 0, len);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert fr != null;
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * FileWriter写出操作，从内存中写出到硬盘文件中
     * 说明:
     * ① 输出操作，对应的File可以不存在的，并不会报异常
     * ② File对应的硬盘中的文件如果不存在，在输出的过程中，会自动创建此文件。
     * ③ FileWriter构造器:
     *    FileWriter(File file, boolean append); (是否在原文件上追加内容)
     *    FileWriter(File file); (相当于append false，对原文件覆盖)
     * ④ write()有重写的方法write(char[] cbuf, 0, len)可以限制写入长度
     *
     * 过程:
     * ① 提供File类的对象，指明写出到的文件
     * ② 提供FileWriter的对象，用于数据的写出
     * ③ 写出的操作write()
     * ④ 流资源的关闭close()
     **/
    @Test
    public void testFileWriter() {
        FileWriter fw = null;
        try {
            // 1.提供File类的对象，指明写出到的文件
            File file = new File("hello.txt");
            // 2.提供FileWriter的对象，用于数据的写出
            fw = new FileWriter(file, false);
            // 3.写出的操作
            fw.write("I have a dream!\n");
            fw.write("good morning!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4.流资源的关闭
            try {
                assert fw != null;
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Description: 使用FileReader和FileWriter将一个文件复制到另一个文件
     */
    @Test
    public void testFileReaderFileWriter() {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            // 1.创建File类的对象，指明读入和写出的文件
            // 不能使用字符流来处理图片等字节数据
            File srcFile = new File("hello.txt");
            File destFile = new File("hello2.txt");
            // 2.创建输入流和输出流的对象
            fr = new FileReader(srcFile);
            fw = new FileWriter(destFile);
            // 3.数据的读入和写出操作
            char[] cbuf = new char[5];
            // 记录每次读入到cbuf数组中的字符的个数
            int len;
            while ((len = fr.read(cbuf)) != -1) {
                // 每次写出len个字符
                fw.write(cbuf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4.关闭两个流资源
            try {
                if (fw != null)
                    fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fr != null)
                    fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Description: FileInputStream, FileOutputStream处理字节流文件
     */
    /*
     *  对于文本文件(.txt,.java,.c,.cpp)，使用字符流处理
     *  对于非文本文件(.jpg,.mp3,.mp4,.avi,.doc,.ppt,...)，使用字节流处理
     * */
    @Test
    public void testFileInputStream() {
        // 过程和FileReader，FileWriter一样
        // 字节流也可以对文本文件进行复制操作，但在内存上System.out可能会有问题
    }
}
