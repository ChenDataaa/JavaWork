package data.structure;


import org.testng.annotations.Test;

/**
 * @Description: 数据结构
 * @create: 2022/01/04 - 19:24
 */
public class Queue {
    /**
     * @Description: 稀疏数组与数组互相转换(围棋)
     */
    @Test
    public void testSparseArray() {
        // 创建原始二维数组 11 * 11
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][2] = 1;
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------");
        int count = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                }
            }
        }
        System.out.println("count = " + count);

        // 创建sparseArray
        int[][] chessArr2 = new int[count + 1][3];
        chessArr2[0][0] = chessArr2[0][1] = 11;
        chessArr2[0][2] = count;
        count = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    chessArr2[count][0] = i;
                    chessArr2[count][1] = j;
                    chessArr2[count][2] = chessArr1[i][j];
                }
            }
        }
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------");

        // 稀疏数组转二维数组
        int[][] chessArr3 = new int[chessArr2[0][0]][chessArr2[0][1]];
        for (int i = 1; i < chessArr2.length; i++) {
            chessArr3[chessArr2[i][0]][chessArr2[i][1]] = chessArr2[i][2];
        }
        for (int[] row : chessArr3) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

    @Test
    public void test() {
        System.out.println(-2 % 5);
    }
}

/**
 * @Description: 使用数组模拟队列Queue，只能使用一次，无法复用，需要环形队列改进
 */
class ArrayQueue {
    private int maxSize; // 队列最大规模
    private int begin;   // 队列首坐标
    private int end;     // 队列尾坐标
    private int[] arr;   // 队列

    // 创建队列构造器(传入队列最大Size)
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        begin = -1; // 初始时指向队列头前一个位置
        end = -1;  // 指向队列尾最(包含队列最后一个数据)
    }

    // 判断是否存满
    private boolean isFull() {
        return end == maxSize - 1;
    }

    // * 判断队列是否为空: begin和end坐标是否相等
    private boolean isEmpty() {
        return begin == end;
    }

    // 添加数据
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        end++;  // 使末尾后移
        arr[end] = n;
    }

    // 获取队列数据，头部begin出队
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        begin++;
        return arr[begin];
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = begin + 1; i <= end; i++) {
            System.out.printf("arr[%d] = %d\n", i, arr[i]);
        }
    }

    // 返回队列首位
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return arr[begin + 1];
    }
}

/**
 * @Description: 环形队列（重点）
 */
/*
 * 重点: 1.判断队列是否已满: (end + 1) % maxSize == begin
 *       2.队列已存储的长度: int count = (end - begin + maxSize) % maxSize
 */
class CircleQueue {
    private int maxSize; // 队列最大规模（多一个动态坐标）
    private int begin;   // 队列首坐标
    private int end;     // 队列尾坐标的下一个坐标
    private int[] arr;   // 队列

    // 创建队列构造器(传入队列最大Size)
    public CircleQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        begin = 0; // 初始化为0
        end = 0;   // 初始化为0
    }

    // * 判断是否存满，end + 1如果和begin重合则表示存满（end和begin之间有一个空位）
    // (end + 1) % maxSize == begin
    private boolean isFull() {
        return (end + 1) % maxSize == begin;
    }

    // * 判断队列是否为空: begin和end坐标是否相等
    private boolean isEmpty() {
        return begin == end;
    }

    // 添加数据
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        arr[end] = n;
        end = (end + 1) % maxSize;  // 使末尾后移
    }

    // 获取队列数据，头部begin出队
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        int value = arr[begin];
        begin = (begin + 1) % maxSize;
        return value;
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        // * 遍历
        // 求出队列有效数据
        int count = (end - begin + maxSize) % maxSize;
        for (int i = begin; i <= begin + count; i++) {
            // 注意此时 i 应为 i % maxSize
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    // 返回队列首位s
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return arr[begin];
    }
}