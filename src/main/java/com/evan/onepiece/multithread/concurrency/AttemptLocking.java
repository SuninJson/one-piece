package com.evan.onepiece.multithread.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Evan Huang
 * @date 2018/4/27
 */
@Slf4j
public class AttemptLocking {
    private ReentrantLock lock = new ReentrantLock();

    public ReentrantLock getLock() {
        return lock;
    }

    public void untimed() {
        boolean captured = lock.tryLock();
        try {
            log.info("tryLock():{}", captured);
        } finally {
            if (captured) {
                lock.unlock();
            }
        }
    }

    public void timed() {
        boolean captured;
        try {
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            log.info("tryLock(2, TimeUnit.SECONDS):{}", captured);
        } finally {
            if (captured) {
                lock.unlock();
            }
        }
    }
}
