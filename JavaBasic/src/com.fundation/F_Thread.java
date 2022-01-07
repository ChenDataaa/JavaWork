package com.fundation;

/**
 * @Description: 线程
 * @create 2021/12/26 - 12:40
 */
public class F_Thread {
/*
    每个线程有一个独立的程序计数器和虚拟机栈，但多个线程共享一个堆和方法区，
    多个线程共享操作数据就有安全隐患，需要用线程同步解决
    并行: 多个CPU同时执行多个任务; 并发: 一个CPU"同时"(快速切换)执行多个任务(秒杀)
*/

/*
    # 1.线程的创建和使用
    I.方式一: 继承于Thread类:
    ① 城建一个继承于Thread类的子类
    ② 重写Thread类的run()方法，将线程将要执行的操作声明在run()中
    ③ 创建Thread类的子类的对象
    ④ 通过此对象调用start()方法作用: ①启动当前线程 ② 调用当前线程的run()
    ⑤ 注意不能通过run()方法启动线程，使用run()相当于在主线程调用对象的类方法，
       不可以让已经start()的线程再次执行，会报告IllegalThreadStateException
    ⑥ 在线程子类对象的this等同于Thread.currentThread()
*/

    //    例: 遍历100以内的偶数
    public static void main(String[] args) {
        SubThread t = new SubThread();
        SubThread t1 = new SubThread();
        t.start(); //不可以让已经start()的线程再次执行
        t1.start();
        //匿名子类匿名对象方法不用创建新的类
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + "匿名");
                }
            }
        }.start();
        for (int i = 0; i < 50; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
/*
    # 2.Thread类常用方法:
    ① start(): 启动当前线程；调用当前线程的run()
    ② run(): 通常需要重写Thread类中的此方法，将创建的线程要执行的操作声明在此方法中
    ③ currentThread():静态方法(可以直接从Thread.currentThread()调用)，返回执行当
       前代码的线程
    ④ getName(): 获取当前线程的名字
    ⑤ setName(): 设置当前线程的名字，或者提供一个形参为String的构造器提供线程名称
    ⑥ yield(): 释放当前cpu的执行权，也有可能下一刻CPU又分配到此线程
    ⑦ join(): 在线程a中调用线程b的join()，此时线程a就进入阻塞状态，直到线程b完全执行
       完以后，线程a才结束阻塞状态。适用于某线程中途需要另一线程的数据，中途暂停此线程，
       让另一线程运行完成(join另一线程)后再执行
    ⑧ sleep(long millisecond): (Thread类静态方法) 让当前线程"睡眠"(阻塞)指定毫秒
    在指定的毫秒时间内，当前线程是阻塞状态，CPU无法分配给此线程资源
    ⑨ isAlive(): 判断当前线程是否存活
    ⑩ wait(): 阻塞此线程并会自动释放锁
       notify()/notifyAll(): 唤醒被wait()的线程
*/
        SubThread t2 = new SubThread();
//        给分线程命名
        t2.setName("线程3");
        t2.start();
//        给主线程命名
        Thread.currentThread().setName("主线程");
//        还可以在Thread子类中建立构造器public SubThread(String name)命名Thread

/*
    # 3.线程的优先级:
    ① Thread定义了三个优先级常量:
    MAX_PRIORITY: 10
    MIN_PRIORITY: 1
    NORM_PRIORITY: 5  --> 默认优先级
    ② 如何获取和设置当前线程的优先级:
    getPriority(): 获取线程的优先级
    setPriority(int p): 设置线程的优先级
    Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
    说明: 高优先级的线程要抢占低优先级线程CPU的执行权。但是只是从概率上讲，高优先级的线程
    高概率的情况下被执行。并不意味着只有当高优先级的线程执行完以后，低优先级的线程才执行
*/
/*
    # II.创建线程方式二: 实现Runnable接口
    ① 创建一个实现了Runnable接口的类
    ② 实现Runnable中的抽象方法: run()
    ③ 创建实现类的对象
    ④ 将此对象作为参数传递到Thread类的构造器中，创建Thread类的对象
    ⑤ 通过Thread类的对象调用start()

    比较创建线程的两种方式
    开发中优先选择: 实现Runnable接口的方式
    原因:
    1. 实现的方式没有类的单继承性的局限性，继承了Thread没办法继承别的父类
    2. 实现的方式更适合来处理多个线程有共享数据的情况。
    联系：Thread类本身也implements实现了Runnable接口
    相同点：两种方式都需要重写run()，将线程要执行的逻辑声明在run()中

    III.创建线程方式三: JDK5.0新增: 实现Callable接口
    实现Callable接口的方式创建多线程比实现Runnable接口创建多线程方式更强大:
    ① 实现Callable接口重写call()方法，实现Runnable接口重写run()方法
    ② call()可以有返回值的
    ③ call()可以抛出异常，被外面的操作捕获，获取异常的信息
    ④ Callable是支持泛型的
    过程:
    ① 创建一个实现Callable的实现类
       Class SubCall implements Callable(){};
    ② 实现call方法，将此线程需要执行的操作声明在call()中
       @Override call(){};
    ③ 创建Callable接口实现类的对象
       SubCall sc = new SubCall();
    ④ 将此Callable接口实现类的对象作为传递到FutureTask构造器中，创建FutureTask的对象
       FutureTask< > ft = new FutureTask< >(sc);
    ⑤ 将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象并调用start()
       new Thread(ft).start();
    ⑥ 获取Callable中call方法的返回值(如果没有返回值就不用get())
       tf.get();


    IV.创建线程方式四: JDK5.0新增: 线程池API: ExecutorService & Executors
    思想: 提前创建多个线程放入线程池中，使用时直接获取，使用完后放回池中，避免频繁创建
          销毁，实现重复利用 (例: 公共汽车)
    好处:
    ① 提高响应速度(减少了创建新线程的时间)
    ② 降低资源消耗(重复利用线程池中线程，不需要每次都创建)
    ③ 可以设置线程池的属性，便于线程管理:
       corePoolSize: 核心池的大小
       maximumPoolSize: 最大线程数
       keepAliveTime: 线程没有任务时最多保持多长时间后会终止
*/
        SubRunnable r = new SubRunnable();
        Thread t3 = new Thread(r); // 将Runnable对象作为实参传入Thread类
        // t3线程
        t3.start();

/*
    # 5.线程的生命周期: 新建->就绪->阻塞->运行->死亡
    线程安全问题由于多个线程共享数据，其他线程在前线程正在操作数据时也参与来操作数据
    解决方式: 当一个线程操作共享数据时，其他线程不能参与，直到线程操作完成数据后，
    其他线程才可以开始操作共享数据，此情况下即使正在操作数据的线程阻塞也不能改变。
*/

/*
    # 6.线程安全问题(三种方式)
    ① 在Java中，我们通过同步机制，来解决线程的安全问题。
    JDK5.0之前的两种解决方式:
    I.方式一: 同步代码块
      synchronized(同步监视器){
          //需要被同步的代码
          //操作共享数据的代码需要被同步
      }
      说明:
      1.操作共享数据的代码，即为需要被同步的代码。
      2.共享数据: 多个线程共同操作的变量，例如卖票ticket就是共享数据。
      3.同步监视器("锁")，任何一个类的对象都可以充当锁。但多个线程必须要共用同一把锁。
      4.在实现Runnable接口创建多线程的方式中，我们可以考虑使用this充当同步监视器。
      5.在继承Thread类创建多线程的方式中，慎用this充当同步监视器，考虑使用当前类充当
        同步监视器(SubThread.class)

    II.方式二: 同步方法
      如果操作共享数据的代码完整的声明在一个方法中，不妨将此方法声明同步的
      public synchronized void manipulateData(){}
      同步方法总结:
      1.同步方法仍然涉及到同步监视器，只是不需要我们显式的声明
      2.非静态的同步方法，同步监视器是: "this"
        静态的同步方法，同步监视器是: 当前类本身

    总结:
       同步的方式，解决了线程的安全问题。
       操作同步代码时，只能有一个线程参与，其他线程等待。相当于是单线程的过程，效率低。

    JDK5.0之后解决线程同步:
    III.方式三: Lock锁
      Lock是控制多个线程对共享资源进行访问的工具
      // 先建立锁对象
      private ReentrantLock lock = new ReentrantLock();
      lock.lock(); --> 需要加锁的代码块 --> lock.unlock();
      1.面试题: synchronized 与 Lock的异同？
        相同: 二者都可以解决线程安全问题
        不同: synchronized机制在执行完相应的同步代码以后，自动的释放同步监视器
        Lock需要手动的锁定同步lock()，结束同步也需要手动解锁unlock()
      2.优先使用顺序:
        Lock->同步代码块(已经进入了方法体，分配了相应资源)->同步方法(在方法体之外)
*/

/*
    # 7.线程死锁问题DeadLock
    死锁: 不同线程分别占用对方需要的同步资源不放弃，都在等待对方放弃自己需要的同步资源
          死锁不会出现异常也不会出现提示，所有线程都处于阻塞状态无法继续
          使用同步时要避免死锁
*/

/*
    # 8.线程的通信
    例: 两个线程交替打印(Communication)
    wait(): 阻塞此线程并会自动释放锁
    notify()/notifyAll(): 唤醒被wait()的线程
    说明:
    ① wait(),notify(),notifyAll()只能出现在同步代码块或同步方法中(Lock不可以)
    ② wait(),notify(),notifyAll()的调用者必须是同步代码块/同步方法中的同步监视器
    ③ wait(),notify(),notifyAll()三个方法定义在java.lang.Object类中(由于任何对象都
       可充当同步监视器，需要任何对象都有这三个方法)

    面试题: sleep()和wait()异同:
    1.相同点: 一旦执行方法，都可以使得当前的线程进入阻塞状态。
    2.不同点:
      ① 两个方法声明的位置不同: Thread类中声明sleep(); Object类中声明wait()
      ② 调用的要求不同: sleep()可以在任何需要的场景下调用;
         wait()必须使用在同步代码块或同步方法中
      ③ 是否释放同步监视器: 如果两个方法都使用在同步代码块或同步方法中，sleep()不会释放锁;
         wait()会释放锁
*/

/*
    # 9.
*/

    }
}


/**
 * @Description: 创建线程的方式一
 */
class SubThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            if (i % 2 == 0) {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(getName() + ": " + i);
            }
        }
    }
}


/**
 * @Description: 创建线程方式二
 */
class SubRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
}