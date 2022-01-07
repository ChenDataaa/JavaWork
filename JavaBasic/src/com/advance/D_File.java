package com.advance;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @Description: IO流，File类的使用
 * @create: 2021/12/31 - 22:08
 */
/*
 * 一、File类的使用:
 *  ① File类声明在java.io包下;
 *  ② File类的一个对象，代表一个文件或一个文件目录(文件夹);
 *  ③ File类中涉及到关于文件或文件目录的创建、删除、重命名、修改时间、文件大小等方法，
 *     并未涉及到写入或读取文件内容的操作; 如果需要读取或写入文件内容，必须使用IO流完成;
 *  ④ 后续File类的对象常会作为参数传递到流的构造器中，指明读取或写入的"终点"
 *
 * 二、如何创建File类的实例:
 *   File(String filePath);
 *   File(String parentPath, String childPath);  // 文件
 *   File(File parentFile, String childPath);
 *
 *   在@Test中当前路径是module，在main方法中当前路径为project
 */
public class D_File {
    /**
     * @Description: File构造器
     */
    @Test
    public void test1() {
        //构造器1
        File file1 = new File("hello.txt");//相对于当前module
        File file2 = new File("C:\\Users\\bingc\\Desktop\\JavaWork\\JavaBasic\\src\\com\\advance\\hello.txt");
        System.out.println(file1);
        System.out.println(file2);
        //构造器2:
        File file3 = new File("C:\\Users\\bingc\\Desktop\\JavaWork", "JavaBasic");
        System.out.println(file3);
        //构造器3:
        File file4 = new File(file3, "hello.txt");
        System.out.println(file4);
    }

    /**
     * @Description: File类的常用方法
     */
    /* 三、File类的常用方法:
     *  String getAbsolutePath(): 获取绝对路径
     *  String getPath(): 获取路径
     *  String getName(): 获取名称
     *  String getParent(): 获取上层文件目录路径; 若无，返回null
     *  long length(): 获取文件长度(字节数); 不能获取目录的长度
     *  long lastModified(): 获取最后一次的修改时间，毫秒值
     *
     *  如下的两个方法适用于文件目录:
     *  String[] list(): 获取指定目录下的所有文件或者文件目录的名称数组
     *  File[] listFiles(): 获取指定目录下的所有文件或者文件目录的File数组
     *
     *  # boolean renameTo(File dest): 重命名文件(要求被重命名的文件存在，dest文件不存在)
     *  boolean isDirectory(): 判断是否是文件目录
     *  boolean isFile(): 判断是否是文件
     *  boolean exists(): 判断是否在硬盘上存在
     *  // boolean canRead(): 判断是否可读
     *  // boolean canWrite(): 判断是否可写
     *  // boolean isHidden(): 判断是否隐藏
     */
    @Test
    public void test2() {
        File file1 = new File("hello.txt");
        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.getPath());
        System.out.println(file1.getName());
        System.out.println(file1.getParent()); // 相对路径下看不到ParentPath
        System.out.println(file1.length()); // 文件内容长度
        System.out.println(new Date(file1.lastModified()));
        System.out.println("----------------------------------------");

        // 打印目录下文件list()
        File file2 = new File("C:\\Users\\bingc\\Desktop\\JavaWork\\JavaBasic");
        String[] l = file2.list();
        assert l != null;
        for (String i : l) {
            System.out.println(i);
        }

        // listFiles(): 返回File类型(绝对路径)
        System.out.println("----------------------------------------");
        File[] files = file2.listFiles();
        assert files != null;
        for (File f : files) {
            System.out.println(f);
        }
    }

    /* 四、File创建文件:
     * ① 创建硬盘中对应的文件或文件目录:
     *    boolean createNewFile(): 创建文件。若文件存在，则不创建，返回false
     *    boolean mkdir(): 创建文件目录; 如果此文件目录存在或上层目录不存在，则不创建;
     *    boolean mkdirs(): 创建文件目录; 如果此文件目录存在，就不创建了;
     *                      如果上层文件目录不存在，一并创建上层目录;
     * ② 删除磁盘中的文件或文件目录:
     *    boolean delete(): 删除文件或者文件夹
     *    (删除注意事项: Java中的删除不走回收站)
     */
    @Test
    public void test3() throws IOException {
        // 文件的创建
        File file1 = new File("hello.txt");
        if (!file1.exists()) {
            if (file1.createNewFile()) {
                System.out.println("创建成功");
            } else {
                System.out.println("创建失败");
            }
        } else {
            System.out.println("文件已存在");
        }

        // 文件目录的创建
        // file.mkdir(); file.mkdirs()
    }
}
