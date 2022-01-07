package com.advance;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @Description: RandomAccessFile => 多应用于下载任务，有seek()功能
 * @create: 2022/01/02 - 11:18
 */

/*
 * RandomAccessFile的使用
 * 1.RandomAccessFile直接继承于java.lang.Object类，实现了DataInput和DataOutput接口
 * 2.RandomAccessFile既可以作为一个输入流，又可以作为一个输出流
 * 3.如果RandomAccessFile作为输出流时，写出文件如果不存在，则在执行过程中自动创建;
 *   如果写出到的文件存在，则会对原有文件内容进行覆盖 (默认情况下，从头覆盖)
 * 4.可以通过相关的操作，实现RandomAccessFile“插入”数据的效果
 */
public class J_RandomAccessFile {
    /*
     * RandomAccessFile作为文件输入流又作为文件输出流
     **/
    @Test
    public void test1() throws IOException {
        // "r"作为输入流
        RandomAccessFile raf1 = new RandomAccessFile(new File("dog.jpg"), "r");
        // "rw"作为输出流
        RandomAccessFile raf2 = new RandomAccessFile(new File("dog.jpg"), "rw");
        byte[] cbuf = new byte[1024];
        int len;
        while ((len = raf1.read(cbuf)) != -1) {
            raf2.write(cbuf, 0, len);
        }
        raf1.close();
        raf2.close();
    }

    /*
     * 如果RandomAccessFile作为输出流时，写出文件如果不存在，则在执行过程中自动创建;
     * 如果写出到的文件存在，则会对原有文件内容进行覆盖 (默认情况下，从头覆盖)
     * */
    @Test
    public void test2() throws IOException {
        RandomAccessFile raf1 = new RandomAccessFile("hello.txt", "rw");
        // raf1.write("xyz".getBytes()); // 默认开头前三个字符覆盖为"xyz"
        raf1.seek(3); // 将指针调到角标为3的位置
        raf1.write("xyz".getBytes());  // 从指针为3的位置开始覆盖
        raf1.close();
    }

    /*
     * 实现在指针后插入(而非覆盖)效果:
     *   在指针后存储后续字符串，在指针处write后用添存储的字符串覆盖后续内容
     * */
    @Test
    public void test3() throws IOException {
        RandomAccessFile raf1 = new RandomAccessFile("hello.txt", "rw");
        raf1.seek(3);//将指针调到角标为3的位置
        // 保存指针3后面的所有数据到StringBuilder中
        StringBuilder builder = new StringBuilder((int) new File("hello.txt").length());
        byte[] buffer = new byte[20];
        int len;
        while ((len = raf1.read(buffer)) != -1) {
            builder.append(new String(buffer, 0, len));
        }
        // 调回指针，写入"xyz"
        raf1.seek(3);
        raf1.write("xyz".getBytes());
        // 将StringBuilder中的数据写入到文件中
        raf1.write(builder.toString().getBytes());
        raf1.close();
    }
}
