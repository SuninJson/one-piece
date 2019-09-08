package com.evan.onepiece.multithread.concurrency;

/**
 * 同步控制EvenGenerator
 *
 * @author Evan Huang
 * @date 2018/4/26 19:46
 */
public class SynchronizedEvenGenerator extends IntGenerator {

    private int currentEvenValue;

    @Override
    public synchronized int next() {
        ++currentEvenValue;
        Thread.yield();
        ++currentEvenValue;
        return currentEvenValue;
    }
}
