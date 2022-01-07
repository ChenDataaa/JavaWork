package com.advance;

import org.junit.Test;

import java.io.*;

/**
 * @Description: 标准的输入，输出流; 打印流; 数据流
 * @create: 2022/01/01 - 19:06
 */
/*
 * 其他流的使用
 * ① System.in, System.out: 标准的输入，输出流
 * ② PrintStream, PrintWriter: 打印流
 * ③ DataInputStream, DataOutputStream: 数据流
 *
 * */
public class H_OtherStream {
    /*
     * 一、标准的输入、输出流:
     *   System.in: 标准的输入流，默认从键盘输入
     *   System.out: 标准的输出流，默认从控制台输出
     *   System类的setIn(InputStream is) / setOut(PrintStream ps)方式重新
     *    指定输入和输出的流
     *
     * 练习:
     * 从键盘输入字符串，要求将读取到的整行字符串转成大写输出。然后继续进行输入操作，
     * 直至当输入"e"或者"exit"时，退出程序
     * 方法一: 使用Scanner实现，调用next()返回一个字符串
     * 方法二: 使用System.in实现; System.in -> 转换流 -> BufferedReader的
     *         readLine()
     */
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            // 通过BufferedReader控制System.out
            InputStreamReader isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);
            while (true) {
                System.out.println("请输入字符串：");
                String data = br.readLine();
                if ("e".equalsIgnoreCase(data) || "exit".equalsIgnoreCase(data)) {
                    System.out.println("程序结束");
                    break;
                }
                String upperCase = data.toUpperCase();
                System.out.println(upperCase);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
     * PrintStream + FileOutputStream将控制台输出转为输出到到文件中
     * */
    @Test
    public void test() {
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream(new File("hello.txt"));
            // 创建打印输出流,设置为自动刷新模式(写入换行符或字节 '\n' 时都会刷新输出缓冲区)
            ps = new PrintStream(fos, true);
            if (ps != null) {// 把标准输出流(控制台输出)改成文件
                System.setOut(ps);
            }
            for (int i = 0; i <= 255; i++) { // 输出ASCII字符
                System.out.print((char) i);
                if (i % 50 == 0) { // 每50个数据一行
                    System.out.println();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    /*
     * 3.数据流
     * 3.1 DataInputStream, DataOutputStream
     * 3.2 作用: 用于读取或写出基本数据类型的变量或字符串
     *
     * 练习: 将内存中的字符串、基本数据类型的变量写出到文件中
     * */
    @Test
    public void test1() throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("hello.txt"));
        dos.writeUTF("hello");
        dos.flush();// 刷新操作，将内存中的数据写入文件
        dos.writeInt(23);
        dos.flush();
        dos.writeBoolean(true);
        dos.flush();
        dos.close();
    }

    /*
     * 将文件中存储的基本数据类型变量和字符串读取到内存中，保存在变量中
     * 注意点：读取不同类型的数据的顺序要与当初写入文件时，保存的数据的顺序一致！
     */
    @Test
    public void test2() throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream("data.txt"));
        String name = dis.readUTF();
        int age = dis.readInt();
        boolean isMale = dis.readBoolean();

        System.out.println("name = " + name);
        System.out.println("age = " + age);
        System.out.println("isMale = " + isMale);

        dis.close();

    }
}