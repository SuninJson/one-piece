package com.evan.onepiece.multithread;

import java.util.concurrent.Callable;

/**
 * @author Evan Huang
 * @date 2018/4/19 21:01
 */
public class TaskWithResult implements Callable<String>{
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }


    @Override
    public String call() throws Exception {
        return "result of TaskWithResult" + id;
    }
}
