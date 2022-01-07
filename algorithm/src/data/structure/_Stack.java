package data.structure;

import org.testng.annotations.Test;

import java.util.Stack;

/**
 * @Description: Stack栈的使用，LIFO后入先出
 * @create: 2022/01/05 - 23:15
 */
public class _Stack {

    @Test
    public void testStack() {
        Stack<String> stack = new Stack<>();
        stack.add("Jack");
        stack.add("Alice");
        stack.add("Tom");

        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}
