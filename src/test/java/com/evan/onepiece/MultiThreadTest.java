package com.evan.onepiece;

import com.evan.onepiece.multithread.LiftOff;
import com.evan.onepiece.multithread.TaskWithResult;
import com.evan.onepiece.multithread.concurrency.AttemptLocking;
import com.evan.onepiece.multithread.concurrency.EvenChecker;
import com.evan.onepiece.multithread.concurrency.MutexEvenGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Evan Huang
 * @date 2018/4/19 16:40
 */
@Slf4j
public class MultiThreadTest {

    @Test
    public void testLiftOff() {
        int i = 10;
        while (i < 30) {
            Thread liftOff = new Thread(new LiftOff(i++));
            liftOff.start();
        }
    }

    @Test
    public void testLiftOffWithCachedThreadPool() {
        int i = 10;
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        while (i < 30) {
            executorService.execute(new LiftOff(i++));
        }
        //调用shutdown()方法防止新任务被继续提交给该Executor
        executorService.shutdown();
    }

    @Test
    public void testCallAble() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(executorService.submit(new TaskWithResult((i))));
        }
        for (Future<String> res : results) {
            try {
                System.out.println(res.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                executorService.shutdown();
            }
        }
    }

    @Test
    public void testEvenChecker() {
        int i = 0;
        while (i < 1000) {
//            EvenChecker.test(new EvenGenerator());
            EvenChecker.test(new MutexEvenGenerator());
            i++;
        }
    }

    @Test
    public void testAttemptLocking() {
        final AttemptLocking attemptLocking = new AttemptLocking();
        attemptLocking.untimed();
        attemptLocking.timed();
        //现在创建一个单独的任务来抓取锁
        new Thread() {
            {
                setDaemon(true);
            }

            @Override
            public void run() {
                attemptLocking.getLock().lock();
                log.info("acquired!");
            }
        }.start();

        Thread.yield();
        attemptLocking.untimed();
        attemptLocking.timed();
    }

}
