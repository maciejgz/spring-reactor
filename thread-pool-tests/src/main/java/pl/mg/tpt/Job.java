package pl.mg.tpt;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Job {


    @Async(value = "testExecutor")
    public void runJob(int i) {
        log.info("run " + i);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("finished " + i);
    }

}
