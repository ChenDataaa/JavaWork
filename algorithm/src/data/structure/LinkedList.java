package data.structure;

import org.testng.annotations.Test;

/**
 * @Description: 链表
 * @create: 2022/01/05 - 16:50
 */
public class LinkedList {
    @Test
    public void testSingleLinkedList() {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "林冲", "豹子头");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "卢俊义", "玉麒麟");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        // 此时add没有加入排名比较
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
        singleLinkedList.del(3);
        singleLinkedList.reverse();
        singleLinkedList.list();
    }

    @Test
    public void testCircleLinkedList() {
        CircleLinkedList circleLinkedList = new CircleLinkedList();
        circleLinkedList.addBoy(5);
        circleLinkedList.showBoy();
    }
}

/**
 * @Description: 定义SingleLinkedList管理HeroNode
 */
class SingleLinkedList {
    // 初始化头节点
    private final HeroNode head = new HeroNode(0, "", "");

    // 添加节点方式一
    public void add(HeroNode node) {
        // 因为头节点不能动，因此我们需要一个辅助变量temp
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    // * 添加节点方式二: 按顺序添加
    public void addByOrder(HeroNode node) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > node.no) {
                break;
            } else if (temp.next.no == node.no) {
                throw new RuntimeException("节点已存在");
            }
            temp = temp.next;
        }
        node.next = temp.next;
        temp.next = node;
    }

    // 修改节点
    public void update(HeroNode node) {
        if (head.next == null) {
            throw new RuntimeException("链表为空");
        }
        HeroNode temp = head;
        while (temp.next != null) {
            if (temp.next.no == node.no) {
                node.next = temp.next.next;
                temp.next = node;
                return;
            }
            temp = temp.next;
        }
        throw new RuntimeException("节点未找到");
    }

    // 删除节点（见leetcode）
    public void del(int no) {
        // 节点们no值都是不重复的
        HeroNode temp = head;
        while (temp.next != null) {
            if (temp.next.no == no) {
                temp.next = temp.next.next;
            }
            temp = temp.next;
        }
    }

    // 链表反转
    public void reverse() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        HeroNode pre = null;
        while (temp != null) {
            HeroNode next = temp.next;
            temp.next = pre;
            pre = temp;
            temp = next;
        }
        head.next = pre;
    }

    // 遍历节点
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

/**
 * @Description: 单链表类
 */
class HeroNode {
    // 定义一个HeroNode，每个HeroNode对象就是一个节点
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; // 指向下一个节点

    // 建造构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    // 重写toString()
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

// *********************************************************

/**
 * @Description: 创建单向环形链表
 */
class CircleLinkedList {
    // 创建一个没有编号的first节点
    public Boy first = new Boy(-1);

    // 创建环形链表
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("数字不正确");
        }

        Boy cur = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.next = first;
                cur = first;
            } else {
                cur.next = boy;
                cur = cur.next;
                cur.next = first;
            }
        }
    }

    // 打印链表
    public void showBoy() {
        if (first.no == -1) {
            System.out.println("链表为空");
            return;
        }
        Boy boy = first;
        do {
            System.out.println(boy);
            boy = boy.next;
        } while (boy != first);
    }
}

/**
 * @Description: 用于构建约瑟夫Joseph环形链表
 */
class Boy {
    public int no; // 编号
    public Boy next;

    public Boy(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}