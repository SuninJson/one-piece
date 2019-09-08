package think.in.concurrency.chain.processor;

import lombok.extern.slf4j.Slf4j;
import think.in.concurrency.chain.task.SimpleTask;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 支持责任链模式的并发处理器
 *
 * @author Huang Sen
 */
@Slf4j
public abstract class ChainedProcessor extends Thread implements Processor {

    private Processor nextProcessor;
    private volatile boolean isRunning = true;
    private BlockingQueue<SimpleTask> taskQueue = new LinkedBlockingDeque<>();

    public void setNextProcessor(Processor processor) {
        if (processor instanceof ChainedProcessor) {
            ((ChainedProcessor) processor).start();
        }
        this.nextProcessor = processor;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                //阻塞式的获取任务信息
                SimpleTask task = taskQueue.take();
                //具体的处理
                doProcessor(task);
                if (nextProcessor != null) {
                    //将任务交给责任链中的下一个处理者
                    nextProcessor.process(task);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void shutdown() {
        isRunning = false;
        if (nextProcessor != null && nextProcessor instanceof ChainedProcessor) {
            ((ChainedProcessor) nextProcessor).shutdown();
        }
        log.info("{}成功关闭！", this.getClass().getSimpleName());
    }

    @Override
    public void process(SimpleTask task) {
        //todo 根据实际业务需求做一些处理
        taskQueue.add(task);
    }

    /**
     * 交给子类实现具体的处理方式
     *
     * @param task 需要处理的任务
     */
    protected abstract void doProcessor(SimpleTask task);
}
