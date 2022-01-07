/**
 * @create 2021/12/27 - 11:07
 */
public class Communication {
    public static void main(String[] args) {
        Number num = new Number();
        Thread t1 = new Thread(num);
        Thread t2 = new Thread(num);

        t1.start();
        t2.start();
    }
}

class Number implements Runnable {
    private int number = 1;

    @Override
    public void run() {
//      while要在notify()之前否则死锁
//      wait(),notify(),notifyAll()的调用者必须是同步代码块/同步方法中的同步监视器
//      this也设为obj，后续notify和wait需要改为obj.wait(),obj.notify()
        synchronized (this) {
            while (number < 50) {
                notify(); // 省略的是this.notify()
                System.out.println(Thread.currentThread().getName() + ": " + number);
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                number++;
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}