package com.fundation;

/*
    * 使用Scanner类从键盘获取不同类型的变量
    1.导入包: import java.util.Scanner;
    2.创建Scanner对象: Scanner input = new Scanner(System.in);
    3.使用Scanner类的方法获取指定类型的变量
*/
import java.util.Scanner;

public class B_ScannerLoop {
    public static void main(String[] args) {
        // # 1.Scanner类
        // 创建Scanner对象
        Scanner scan = new Scanner(System.in);
        // 输入获取int类型的变量
        // int num = scan.nextInt();
        // System.out.println(num);
        // 输入获取String类型的变量
        System.out.print("请输入姓名: ");
        String name = scan.next();
        System.out.println("你的的姓名是: " + name);
        System.out.print("你姓: " + name.charAt(0));
        scan.close();

        // Math.random() 范围只有从 [0,1)
        // * 选从[a-b)的随机数: Math.random() * (b-a) + a
        int ran = (int) (Math.random() * (90 - 60) + 60);
        System.out.println(ran);
        // * 选从[a-b]的随机数: Math.random() * (b-a+1) + a

        // # 2.switch-case结构语句
        // * switch 只接受6种类型的值: byte, short, char, int, enum, String
        // * case后只能跟常量不能跟范围
        // switch (num) {
        // case 常量1:
        // 执行语句;
        // break;
        // case 常量2: // 如果 常量2,3,4所对应值相等可以省略
        // case 常量3:
        // case 常量4:
        // 执行语句;
        // break;
        // default:
        // 执行语句;
        // }
        int season = 7;
        switch (season) {
            case 1:
            case 2:
            case 3:
                System.out.println("春季");
                break;
            case 4:
            case 5:
            case 6:
                System.out.println("夏季");
                break;
            case 7:
            case 8:
            case 9:
                System.out.println("秋季");
                break;
            default:
                System.out.println("冬季");
                break;
        }
        // 还可以通过switch通过输入的月日计算这是当年的第几天

        // # 3.for循环
        // for初始化语句只执行一次
        // for (①初始化语句; ②条件表达式; ③语句) {④循环体}
        // 顺序是①->②->④->③
        for (int i = 1; i <= 5; i++) {
            System.out.println(i);
        }
        int lo = 1;
        for (System.out.println("a"); lo <= 3; System.out.println("c "), lo++) {
            System.out.print(lo + " ");
            System.out.print("b ");
        }

        // # 4.while(等同于for(;;)), do-while循环
        // while(条件){}
        // do{
        // }while(条件);

        // # 5.嵌套循环, continue, break + label
        // * 可以加入label选择continue和break循环位置
        label: for (int i = 2; i < 100; i++) {
            for (int j = 2; j < Math.sqrt(i); j++) {
                if (i % j == 0) {
                    continue label;
                }
            }
            System.out.println(i);
        }

        // * 嵌套例2
        label: for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 10; j++) {
                if (j % 4 == 0) {
                    continue label;
                }
                System.out.print(j);
            }
            // * 此语句不会执行，会提前continue终止
            System.out.println("good");
        }
        System.out.println();

        // * 嵌套例3，求因子和数 6=3+2+1
        int sum = 1;
        for (int i = 3; i <= 1000; i++) {
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    sum += j;
                    sum += (i / j);
                }
            }
            if (sum == i) {
                System.out.println(i);
            }
            sum = 1;
        }
    }
}
