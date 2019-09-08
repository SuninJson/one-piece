package think.in.concurrency.chain.processor;

import lombok.extern.slf4j.Slf4j;
import think.in.concurrency.chain.task.SimpleTask;

/**
 * 简单的处理器
 *
 * @author Huang Sen
 */
@Slf4j
public class SimpleProcessor extends ChainedProcessor {
    @Override
    protected void doProcessor(SimpleTask task) {
        log.info("对【{}】做一些简单的处理...", task.getTaskName());
    }
}
