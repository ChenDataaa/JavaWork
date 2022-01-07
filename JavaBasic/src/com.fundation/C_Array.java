package com.fundation;

/**
 * @create 2021/12/17 - 12:15
 */

// 数组

// 数组：相同类型数据按顺序集合
// 数组是引用数据类型，引用的是第一个元素的首地址
// * 数组长度一旦确定不可修改

// Arrays类定义了java arrays的操作方法
import java.util.Arrays;

public class C_Array {
    public static void main(String[] args) {
        // # 1.一维数组的使用
        // 引用数据类型要用new初始化
        // * 1.1静态初始化：数组初始化和数组元素赋值同时进行
        int[] ids; // int ids[]也可以
        ids = new int[] { 1001, 1002, 1003, 1004 };
        System.out.println(ids[0]);
        // 也可以不加new int[] (类型推断)
        // int[] id = { 1, 2, 3, 4 };

        // * 1.2动态初始化：数组的初始化和数组元素赋值分开进行
        String[] names = new String[4]; // 动态初始化必须填写元素数量
        // * 动态初始化随后只能用角标方式对数组元素赋值
        // 编译时角标越界不会报错，但运行会报错
        names[0] = "张三";
        names[1] = "李四";
        names[2] = "王五";
        names[3] = "赵六";

        // # 2.用length方法获取数组长度
        System.out.println("names数组长度: " + names.length); // 4

        // # 3.数组元素的默认初始化值
        // 整型数组默认值都是0，浮点型默认是0.0
        // char型默认是char的0->'\u0000'，boolean类型默认是false
        // 引用数据类型默认初始化值是null;

        // # 4.内存结构：堆，栈，方法区(包含常量池和静态域)
        // 栈: 主要存储局部变量
        // 堆: 主要存放new出来的对象(对象和数组)，存放对象实例
        // 例如: int[] arr = new int[]{1,2,3};
        // arr是局部变量在栈中，但new在堆中，栈存放的是首个元素在堆中的地址
        // 如果给arr一个新的new空间，arr指向原来数组的指针消失，
        // 如果此时堆中的数组没有指针指向自己则会被垃圾回收

        // # 5.二维数组
        // * 5.1 二维静态初始化
        // int[][] arr2 = new int[][] { { 1, 2, 3 }, { 4, 6 }, { 7, 9, 10 } };
        // System.out.println(arr2[0][1]);
        // int arr3[][] = {{1,2},{3,4}}; // 可以省略
        // * 5.2 二维数组动态初始化
        // String[][] arr2 = new String[3][2]; // 3行2列(第二个[]可以为空)
        String[][] arr3 = new String[3][];
        // 此时子数组为空，表示空指针，可以进一步动态调整数组大小
        arr3[0] = new String[] { "bad", "bod", "cao" };
        arr3[1] = new String[3];
        arr3[1][1] = "good";
        arr3[2] = new String[5];

        // # 6.计算数组长度
        System.out.println("arr3数组长度: " + arr3.length);
        System.out.println("arr3[2]数组长度: " + arr3[2].length);
        // System.out.println("arr3[1]的地址: " + arr3[1]); // 空指针则是null

        // * 杨辉三角例题
        int[][] tri = new int[10][];
        tri[0] = new int[] { 1 };
        tri[1] = new int[] { 1, 1 };
        for (int i = 2; i < tri.length; i++) {
            tri[i] = new int[i + 1];
            tri[i][0] = 1;
            for (int j = 1; j < tri[i].length - 1; j++) {
                tri[i][j] = tri[i - 1][j] + tri[i - 1][j - 1];
            }
            tri[i][i] = 1;
            System.out.println();
        }
        for (int i = 0; i < tri.length; i++) {
            for (int j = 0; j < tri[i].length; j++) {
                System.out.print(tri[i][j] + " ");
            }
            System.out.println();
        }

        // # 7.数组的复制
        int[] a1, a2;
        a1 = new int[] { 89, 78, 67, 90 };
        // * 注意此时赋值只是a2指向了a1的数组地址，改变a2也会改变a1
        a2 = a1; // ! 此操作不能叫做数组复制，相当于创建快捷方式
        a2[0] += 10;
        System.out.println("a1[0]: " + a1[0] + "; a2[0]: " + a2[0]);

        // for循环复制
        // for(int i=0; i<a1.length ;i++){
        // a2[i] = a1[i];
        // }

        // * clone方法
        a2 = a1.clone();
        a2[0] += 100;
        System.out.println("a1[0]: " + a1[0] + "; a2[0]: " + a2[0]);

        // Arraycopy方法

        // # 8.数组的排序算法(冒泡和快排要会写)
        // * 8.1 冒泡排序
        // 两两相对比，每次都把最大值排到最后
        int[] arr;
        arr = new int[] { 12, -34, 56, 78, -23, 45, 67, -89, 10, -4, 67, 34 };
        // 要经历n-1次大循环
        for (int i = 0; i < arr.length; i++) {
            // 小循环在每次大循环时都会提前一格终止
            for (int j = 0; j < arr.length - i - 1; j++) {
                // 交换元素条件，大的放后面
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("冒泡排序后: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // # 9.Arrays类常用方法
        // boolean equals(int[] a, int[] b) // 判断两个数组是否相等
        // String toString(int[] a) // 数组转字符串
        // void fill(int[] a, int val) // 填充数组
        // void sort(int[] a) // 排序
        // int binarySearch(int[] a, int key) // 二分查找

        // 1.equals判断数组是否相等
        int[] arr5 = new int[] { 8, -2, 38 };
        int[] arr6 = new int[] { 8, 22, -7 };
        System.out.println("arr5和arr6是否相等: " + Arrays.equals(arr5, arr6));
        // 2.toString输出数组信息(字符串形式)
        System.out.println(Arrays.toString(arr6));
        // 3.fill填充数组(每个值都替换为val)
        Arrays.fill(arr6, 100);
        System.out.println("填充后的arr6: " + Arrays.toString(arr6));
        // 4.sort排序
        Arrays.sort(arr5);
        System.out.println("排序后的arr5: " + Arrays.toString(arr5));
        // 5.binarySearch二分查找，返回查找到的index，返回负数为没找到
        int index = Arrays.binarySearch(arr5, -2);
        System.out.println("-2在arr5中的index: " + index);

        // # 10.数组的常见异常
        // 1.ArrayIndexOutOfBoundsException 数组角标越界异常
        // 2.NullPointerException 空指针异常，数组为null时访问数组元素
        int[][] arr7 = new int[3][];
        System.out.println(arr7[0]); // null
        // System.out.println(arr7[0][0]); // 报错
        // 此外对象是null的异常也会抛出
        // String[] arr8 = new String[] { null, "a", "c" };
        // System.out.println(Arrays.toString(arr8[0])); // null
    }
}
