package com.advance;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description: TCP网络编程
 * @create: 2022/01/02 - 14:38
 */
public class M_TCP {
    // 实现TCP的网络编程

    /**
     * @Description: 例1: 客户端发送信息给服务端，服务端将数据显示在控制台上
     */
    // 客户端
    @Test
    public void client() {
        Socket socket = null;
        OutputStream os = null;
        try {
            // 1.创建Socket对象，指明服务器端的ip和端口号
            InetAddress inet = InetAddress.getByName("localhost");
            socket = new Socket(inet, 8899);
            // 2.获取OutputStream输出流，用于输出数据
            os = socket.getOutputStream();
            os.write("你好，我是客户端".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 3.关闭资源
            try {
                assert os != null;
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * 服务端用于接受数据: ServerSocket
     * */
    @Test
    public void server() {
        ServerSocket ss = null;// 指定端口号
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            // 1.创建服务器端Socket，指明自己的端口号
            ss = new ServerSocket(8899);
            // 2.创建自己的Socket ServerSocket accept()方法用于接收客户端的socket
            socket = ss.accept();
            // 3.获取输入流InputStream读取输入流数据: socket.getInputStream()
            is = socket.getInputStream();

            // 不建议以下写法(可能有乱码)
            // byte[] cbuf = new byte[1024];
            // int len;
            // while ((len = is.read(cbuf)) != -1) {
            //     String str = new String(cbuf, 0, len);
            //     System.out.println(str);
            // }

            // 4.获取输入的数据
            // 使用ByteArrayOutputStream转换，字节流会储存在其数组中
            baos = new ByteArrayOutputStream();
            byte[] cbuf = new byte[5];
            int len;
            while ((len = is.read(cbuf)) != -1) {
                baos.write(cbuf, 0, len);
            }
            // 将ByteArrayOutputStream中储存的字节转换为String
            String str = baos.toString();
            System.out.println(str);
            // 查看收到了客户端的地址
            System.out.println("地址: " + socket.getInetAddress().getHostAddress());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 5.关闭流
            try {
                assert baos != null;
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
