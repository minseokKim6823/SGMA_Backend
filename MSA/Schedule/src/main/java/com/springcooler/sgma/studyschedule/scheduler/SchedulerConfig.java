package com.springcooler.sgma.studyschedule.scheduler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig{
    @Bean
    TrackingThreadPoolTaskScheduler trackingThreadPoolTaskScheduler(){
        TrackingThreadPoolTaskScheduler scheduler = new TrackingThreadPoolTaskScheduler();
        scheduler.setPoolSize(5);

        return scheduler;
    }
}
