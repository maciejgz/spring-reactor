package pl.mg.tpt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
@Configuration
@EnableAsync
@Slf4j
public class Starter implements CommandLineRunner {

    private final Job job;

    public Starter(Job job) {
        this.job = job;
    }

    @Bean(name = "testExecutor")
    public Executor additionalExternalSystemsTaskExecutor() {
        log.debug("Creating external systems task executor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(2);
        executor.setThreadNamePrefix("testExecutor");
        executor.initialize();
        log.info("async executor configuration: " + executor);
        return executor;
    }

    @Override
    public void run(String... args) throws Exception {
        Executor executors = Executors.newFixedThreadPool(40);

        for (int i = 0; i < 50; i++) {
            int w = i;
            executors.execute(() -> job.runJob(w));
        }
    }
}
