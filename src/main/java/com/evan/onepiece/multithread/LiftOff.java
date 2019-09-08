package com.evan.onepiece.multithread;

/**
 * 倒计时
 *
 * @author Evan Huang
 * @date 2018/4/19 16:28
 */
public class LiftOff implements Runnable {
    /**
     * 倒计时数
     */
    private int countDown = 10;

    private static int taskCount = 0;

    /**
     * 当前任务id
     */
    private final int id = taskCount++;

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    private String getStatus() {
        StringBuilder result = new StringBuilder();
        result.append("#");
        result.append("(");
        result.append(countDown < 0 ? "end" : countDown + "),");
        return result.toString();
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.print(getStatus());
            //告诉其它线程自己已执行完生命周期中最重要的部分，此时正是切换给其他任务执行一段时间的大好时机
            Thread.yield();
        }
    }
}
