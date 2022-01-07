package com.advance;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @Description: InputStreamReader解码; OutputStreamWriter编码
 * @create: 2022/01/01 - 17:51
 */

/*
 * 处理流之二: 转换流的使用
 * ① 转换流: 属于字符流
 *    InputStreamReader: 将一个字节的输入流转换为字符的输入流 (以某字符集读取)
 *    File -> FileInputStream -> InputStreamReader (解析字节流bytes到字符流char)
 *    OutputStreamWriter: 将一个字符的输出流转换为字节的输出流 (转换字符集写出)
 * ② 作用: 提供字节流与字符流之间的转换
 * ③ 解码: 字节、字节数组 --> 字符数组、字符串
 *    编码: 字符数组、字符串 --> 字节、字节数组
 *
 *
 * ④ 字符集
 *    ASCII: 美国标准信息交换码
 *    用一个字节的7位可以表示
 *    ISO8859-1: 拉丁码表,欧洲码表
 *    用一个字节的8位表示
 *    GB2312: 中国的中文编码表。最多两个字节编码所有字符
 *    GBK: 中国的中文编码表升级，融合了更多的中文文字符号。最多两个字节编码
 *    Unicode: 国际标准码，融合了目前人类使用的所有字符。为每个字符分配唯一的字符码;
 *             所有的文字都用两个字节来表示
 *    UTF-8: 变长的编码方式，可用1-4个字节来表示一个字符。
 **/
public class G_StreamReader {

    /**
     * @Description: File->FileInputStream(字节流)->InputStreamReader(解析字节流)
     */
    // 以下是throws简便操作，实际仍要try-catch-finally
    @Test
    public void test1() throws IOException {
        // 创建字节输入流
        FileInputStream fis = new FileInputStream(new File("hello.txt"));
        // 创建字节输入流InputStreamReader(FileInputStream fis, 字符集);
        // 字符集使用存储文件时字符集，不传入字符集参数则默认解码用UTF-8
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);

        // 将读入的流读取到控制台
        char[] cbuf = new char[10];
        int len;
        while ((len = isr.read(cbuf)) != -1) {
            String str = new String(cbuf, 0, len);
            System.out.println(str);
        }
        // 关闭流(只需关闭最外层流)
        isr.close();
    }

    /**
     * @Description: 综合使用InputStreamReader, OutputStreamWriter
     */
    // 以下应使用try-catch-finally
    @Test
    public void test2() throws IOException {
        // 创建File对象
        File file1 = new File("hello.txt");
        File file2 = new File("hello1.txt");
        // 创建字节输入输出流
        FileInputStream fis = new FileInputStream(file1);
        FileOutputStream fos = new FileOutputStream(file2);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.US_ASCII);

        char[] cbuf = new char[10];
        int len;
        while ((len = isr.read(cbuf)) != -1) {
            osw.write(cbuf, 0, len);
        }

        isr.close();
        osw.close();
    }
}
