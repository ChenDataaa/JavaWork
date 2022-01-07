package com.fundation;

/**
 * @create 2021/12/23 - 17:51
 */
public class TemplateMethod {
    public static void main(String[] args) {
        SubTemplate t = new SubTemplate();
        t.spendTime();
        // new SubTemplate().spendTime();
    }
}

/**
 * 设计计算代码时间的抽象类
 */

abstract class Template {
    // 计算代码花费时间
    public void spendTime() {
        long startTime = System.currentTimeMillis();
        // runCode就是不确定的部分
        runCode();
        long endTime = System.currentTimeMillis();
        System.out.println("runCode花费时间" + (endTime - startTime));
    }

    // 抽象方法以后子类重写此方法
    public abstract void runCode();
}

class SubTemplate extends Template {
    @Override
    public void runCode() {
        for (int i = 2; i <= 1000; i++) {
            boolean isPrime = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                System.out.println(i);
            }
        }
    }
}