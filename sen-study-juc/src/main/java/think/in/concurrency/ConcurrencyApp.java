package think.in.concurrency;

import lombok.extern.slf4j.Slf4j;
import think.in.concurrency.chain.processor.PostProcessor;
import think.in.concurrency.chain.processor.Preprocessor;
import think.in.concurrency.chain.processor.SimpleProcessor;
import think.in.concurrency.chain.task.SimpleTask;

/**
 * @author Huang Sen
 */
@Slf4j
public class ConcurrencyApp {

    public static void main(String[] args) {
        Preprocessor preprocessor = new Preprocessor();
        SimpleProcessor simpleProcessor = new SimpleProcessor();
        PostProcessor postProcessor = new PostProcessor();

        preprocessor.setNextProcessor(simpleProcessor);
        simpleProcessor.setNextProcessor(postProcessor);

        preprocessor.start();

        log.info("开始分发任务...");
        for (int i = 0; i < 10; i++) {
            SimpleTask task = new SimpleTask();
            task.setTaskName("测试任务：" + i);
            preprocessor.process(task);
        }
    }
}
