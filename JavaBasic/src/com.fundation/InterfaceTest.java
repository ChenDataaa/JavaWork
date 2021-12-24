package com.fundation;

interface PP {
    static void ll() {
    }

    public default void mm() {
        System.out.println("1");
    }

    public default void md() {
        mm();
        ll();
    }
}

class Bp implements PP {
    public void todo() {
        PP.super.md();
    }
}

/**
 * @create 2021/12/23 - 19:58
 */
public class InterfaceTest {
    public static void main(String[] args) {

    }
}