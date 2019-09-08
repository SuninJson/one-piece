package think.in.concurrency.chain.processor;

import lombok.extern.slf4j.Slf4j;
import think.in.concurrency.chain.task.SimpleTask;

/**
 * 前置处理器
 *
 * @author Huang Sen
 */
@Slf4j
public class Preprocessor extends ChainedProcessor {


    @Override
    protected void doProcessor(SimpleTask task) {
        log.info("对【{}】做一些前置处理...", task.getTaskName());
    }
}
