package think.in.concurrency.sychronized;

public class ObjectSynchronizedDemo implements Runnable {
    static Integer count=0;
    int x = 100;

    public synchronized void m1() {
        x = 1000;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m1:x=" + x);
    }

    public synchronized void m2() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        x = 2000;
        System.out.println("m2:x=" + x);
    }

    public void syncInvoke() {
            /*
        1、线程一开始尝试执行SynchronizedDemo.m1方法，尝试进行enter monitor
        2、enter monitor成功之后获取到sd对象的MutexLock
        3、开始执行SynchronizedDemo.m1方法
        4、SynchronizedDemo.m1方法将x的值设置为1000后，睡眠1秒（睡眠中释放了cpu资源但并不会释放锁资源）后在控制台打印【m1:x=1000】
         */
        new Thread(this::m1).start();

        /*
        1、线程2开始尝试执行SynchronizedDemo.m2方法，尝试enter monitor
        2、线程1还在睡眠当中，线程2 enter monitor失败，进入同步队列，线程2进入BLOCKED状态
        3、线程1执行完毕后exit monitor，开始通知同步队列中的线程继续抢占MutexLock
        4、线程2 enter monitor,将x的值修改为2000
         */
        new Thread(this::m2).start();

        /*
        被synchronized修饰的非静态方法和同步代码块锁住的都是this对象
         */
        synchronized (this) {
            this.x = 3000;
        }
        System.out.println("Main x=" + this.x);
    }

    public static void incr(){
        synchronized (count) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        SynchronizedDemo sd = new SynchronizedDemo();
//        sd.syncInvoke();

        for(int i=0;i<1000;i++){
            new Thread(ObjectSynchronizedDemo::incr).start();
        }
        Thread.sleep(5000);
        System.out.println("result:"+count);
    }

    @Override
    public void run() {
        m1();
    }
}