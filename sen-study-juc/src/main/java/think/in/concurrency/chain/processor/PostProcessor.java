package think.in.concurrency.chain.processor;

import lombok.extern.slf4j.Slf4j;
import think.in.concurrency.chain.task.SimpleTask;

/**
 * 后置的处理器
 *
 * @author Huang Sen
 */
@Slf4j
public class PostProcessor extends ChainedProcessor {
    @Override
    protected void doProcessor(SimpleTask task) {
        log.info("对【{}】做一些后置处理...", task.getTaskName());
    }
}
