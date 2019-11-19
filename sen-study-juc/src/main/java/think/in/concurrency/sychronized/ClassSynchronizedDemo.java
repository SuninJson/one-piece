package think.in.concurrency.sychronized;

public class ClassSynchronizedDemo {
    static Integer count = 0;

    public static void incr() {
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

        for (int i = 0; i < 1000; i++) {
            new Thread(ClassSynchronizedDemo::incr).start();
        }
        Thread.sleep(5000);
        System.out.println("result:" + count);
    }

}