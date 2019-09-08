package com.evan.onepiece.multithread.concurrency;

/**
 * @author Evan Huang
 * @date 2018/4/23 19:23
 */
public abstract class IntGenerator {
    private volatile boolean canceled = false;

    public abstract int next();

    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }

}
