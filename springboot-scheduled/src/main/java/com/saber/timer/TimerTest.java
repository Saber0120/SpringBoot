package com.saber.timer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TimerTest {

    @Scheduled(cron = "0/1 * * * * ?")
    private void test() {
        System.out.println("执行任务的时间是：" + new Date());
    }
}
