package com.evan.onepiece.multithread.concurrency;

public class EvenGenerator extends IntGenerator {
    private int currentEvenValue;

    @Override
    public int next() {
        ++currentEvenValue;
        Thread.yield();
        ++currentEvenValue;
        return currentEvenValue;
    }
}
