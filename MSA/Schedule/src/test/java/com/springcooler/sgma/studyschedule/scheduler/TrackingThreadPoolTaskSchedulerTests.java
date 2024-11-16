package com.springcooler.sgma.studyschedule.scheduler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.support.PeriodicTrigger;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
@SpringBootTest
@Slf4j
public class TrackingThreadPoolTaskSchedulerTests {

    private TrackingThreadPoolTaskScheduler scheduler;

    @BeforeEach
    public void setUp() {
        scheduler = new TrackingThreadPoolTaskScheduler();
        scheduler.setPoolSize(1);
        scheduler.initialize();
    }

    @Test
    public void testScheduledTasksTracking() {
        // Create some dummy tasks
        Runnable task1 = () -> System.out.println("Task 1 executed");
        Runnable task2 = () -> System.out.println("Task 2 executed");

        // Schedule tasks using the custom scheduler
        ScheduledFuture<?> future1 = scheduler.schedule(task1, new PeriodicTrigger(1000, TimeUnit.MILLISECONDS));
        ScheduledFuture<?> future2 = scheduler.scheduleAtFixedRate(task2, 2000);

        // Retrieve the scheduled tasks
        List<ScheduledFuture<?>> scheduledTasks = scheduler.getScheduledTasks();
        for (ScheduledFuture<?> scheduledTask : scheduledTasks) {
            log.info("scheduledTask: {}", scheduledTask);
        }
        // Verify that the scheduled tasks were tracked correctly
        assertFalse(scheduledTasks.isEmpty(), "Scheduled tasks should not be empty");
        assertEquals(2, scheduledTasks.size(), "There should be exactly 2 scheduled tasks");

        // Optionally, you can check the actual futures if needed
        assertEquals(future1, scheduledTasks.get(0), "First scheduled task does not match");
        assertEquals(future2, scheduledTasks.get(1), "Second scheduled task does not match");
    }

    @Test
    public void simpleTrackingThreadPoolTaskScheduler() throws InterruptedException {
        Instant startingTime2 = Instant.now().plusSeconds(5);
        Runnable task1 = () -> System.out.println("Task 1 executed");
        scheduler.schedule(task1, startingTime2);
        Thread.sleep(6000);
        List<ScheduledFuture<?>> scheduledTasks = scheduler.getScheduledTasks();
        for (ScheduledFuture<?> scheduledTask : scheduledTasks) {
            log.info("scheduledTask: {}", scheduledTask);
        }
    }

}
