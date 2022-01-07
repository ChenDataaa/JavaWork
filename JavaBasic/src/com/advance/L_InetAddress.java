package com.advance;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Description: Java网络
 * @create: 2022/01/02 - 12:47
 */
/*
 * 一、网络编程中有两个主要的问题:
 * 1.如何准确地定位网络上一台或多台主机；定位主机上的特定的应用
 * 2.找到主机后如何可靠高效地进行数据传输
 *
 * 二、网络编程中的两个要素:
 * 1.IP和端口号
 * 2.提供网络通信协议: TCP/IP参考模型(应用层、传输层、网络层、物理+数据链路层)
 *
 * 三、通信要素一: IP和端口号
 * ① IP: 唯一的标识Internet上的计算机(通信实体);
 * ② 在Java中使用InetAddress类代表IP;
 * ③ IP分类: IPv4和IPv6; 万维网和局域网(192.168);
 * ④ 域名: www.baidu.com; www.mi.com; www.sina.com;
 * ⑤ 本地回路地址: 127.0.0.1 <=> localhost;
 * ⑥ 如何实例化InetAddress:两个方法: getByName(String host),getLocalHost();
 *    两个常用方法: getHostName() / getHostAddress();
 * ⑦ 端口号: 正在计算机上运行的进程
 *    要求: 不同的进程有不同的端口号
 *    范围: 被规定为一个 16 位的整数 0~65535
 * ⑧ 端口号与IP地址的组合得出一个网络套接字: Socket;
 * */
public class L_InetAddress {
    public static void main(String[] args) throws UnknownHostException {
        // # 一、实例化IP地址getByName()
        // 创建具体IP地址对象
        // 方式一: 通过具体IP地址
        InetAddress inet1 = InetAddress.getByName("192.168.10.14");
        System.out.println(inet1); // /192.168.56.1
        // 方式二: 通过域名，自动解析IP
        InetAddress inet2 = InetAddress.getByName("www.bilibili.com");
        System.out.println(inet2); // www.bilibili.com/119.3.70.188

        // 二、获取本地IP: 本地回路地址: IP: 127.0.0.1 <=> 域名: localhost
        // InetAddress.getLocalHost()
        // InetAddress inet3 = InetAddress.getByName("localhost");
        InetAddress inet3 = InetAddress.getLocalHost();
        System.out.println(inet3); // BINGCHEN-WIN/192.168.56.1

        // 两个常用方法:
        // getHostName(): 获取域名; getHostAddress(): 获取IP地址
        System.out.println(inet3.getHostName()); // BINGCHEN-WIN
    }
}
