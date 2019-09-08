package think.in.concurrency.chain.processor;

import think.in.concurrency.chain.task.SimpleTask;

/**
 * 处理器
 *
 * @author Huang Sen
 */
public interface Processor {
    void process(SimpleTask tasks);
}
