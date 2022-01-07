package com.advance;

import org.junit.Test;

import java.io.*;

/**
 * @Description: 缓冲流的使用，提高流的读取写入速度
 * @create: 2022/01/01 - 15:29
 */
/*
 * 缓冲流(处理流的一种)(开发中用的多)，内部提供缓冲区，使读取，写入速度快
 * 处理流就是套接在已有流基础之上
 * BufferedInputStream (read(byte[] buffer))
 * BufferedOutputStream (write(byte[] buffer, 0, len) / flush()
 * BufferedReader (read(char[] cbuf) / readLine())
 * BufferedWriter (write(char[] cbuf, 0, len) / flush()
 * */
public class F_BufferedStream {
    /**
     * @Description: BufferedInputStream; BufferedOutputStream包装
     */
    /*
     * BufferedInputStream => 包装FileInputStream;
     * BufferedOutputStream => 包装FileOutputStream;
     */
    @Test
    public void testBufferedStream() {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            // 造File对象
            File srcFile = new File("dog.jpg");
            File destFile = new File("dog1.jpg");
            // 制造文件流(节点流)
            FileInputStream fis = new FileInputStream(srcFile);
            FileOutputStream fos = new FileOutputStream(destFile);
            // 制造缓冲流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);
            // 读取与写入
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 资源关闭(先关外层流，再关闭内层流)
            // 关闭外层时，会自动关闭内层，仅需要关闭外层即可
            try {
                assert bis != null;
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                assert bos != null;
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Description: BufferedReader; BufferedWriter包装用来处理文本文件
     */
    /*
     * BufferedReader => 包装FileReader
     * BufferedWriter => 包装FileWriter
     * 读取写入数据方法基本相同，额外多了readline()方法，但要手动加入换行符
     **/
    @Test
    public void testBufferedReaderWriter() throws IOException {

        // 过程:
        BufferedReader br = new BufferedReader(new FileReader("hello.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("hello.txt"));
        // 方式一: 老方法，仍然使用char[]
        // char[] cbuf = new char[1024];
        // int len;
        // while ((len = br.read(cbuf)) != -1) {
        //     bw.write(cbuf, 0, len);
        // }

        // 方式二: 使用String => readline()(不包含换行符!), 到末尾时返回null
        // 写入时要手动加入换行符
        String data;
        while ((data = br.readLine()) != null) {
            // 方法一: 加入换行符
            // bw.write(data + "\n");
            // 方法二: 不加入换行符
            bw.write(data);
            bw.newLine();
        }
        // 关闭流
        br.close();
        bw.close();
    }
}
