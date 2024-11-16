package com.springcooler.sgma.studyschedule.scheduler;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
public class TrackingThreadPoolTaskScheduler extends ThreadPoolTaskScheduler {
    private final List<ScheduledFuture<?>> scheduledTasks = new ArrayList<>();

    @Override
    public ScheduledFuture<?> schedule(Runnable task, Trigger trigger) {
        ScheduledFuture<?> future = super.schedule(task, trigger);
        scheduledTasks.add(future);
        return future;
    }

    @Override
    public ScheduledFuture<?> schedule(Runnable task, Instant startTime) {
        ScheduledFuture future = super.schedule(task, startTime);
        scheduledTasks.add(future);
        return future;
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long period) {
        ScheduledFuture<?> future = super.scheduleAtFixedRate(task, period);
        scheduledTasks.add(future);
        return future;
    }

    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, long delay) {
        ScheduledFuture<?> future = super.scheduleWithFixedDelay(task, delay);
        scheduledTasks.add(future);
        return future;
    }

    public List<ScheduledFuture<?>> getScheduledTasks() {
        return scheduledTasks;
    }
}
