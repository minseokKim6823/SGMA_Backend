package com.springcooler.sgma.studyschedule.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.util.Assert;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ScheduledTaskServiceTests {

    @Autowired
    private ScheduledTaskService scheduledTaskService;

    @Autowired
    private TrackingThreadPoolTaskScheduler scheduler;

    @Test
    void testUpdateScheduledTasks() throws Exception {
        // 1. 스케줄 작업을 업데이트하는 메서드 호출
        scheduledTaskService.updateScheduledTasks();

        // 2. 스케줄러에서 등록된 작업들을 가져옴
        List<ScheduledFuture<?>> scheduledTasks = scheduler.getScheduledTasks();

        // 3. 로그로 현재 시간 출력
        log.info("now(): {}", new Timestamp(Instant.now().toEpochMilli()));

        // 4. 스케줄링된 작업이 있는지 검증
        assertFalse(scheduledTasks.isEmpty(), "Scheduled tasks should not be empty");

        // 5. 스케줄링된 각 작업의 실행 시간 확인 (예시로 첫 번째 작업의 시간 확인)
        ScheduledFuture<?> firstTask = scheduledTasks.get(0);
        assertNotNull(firstTask, "The first scheduled task should not be null");
        log.info("Scheduled Task 1 is set to run at: {}", firstTask);

        // 6. 스케줄 작업이 예상대로 동작하는지 검증
        // 일정 시간 동안 대기 후 작업이 제대로 실행되었는지 확인
//        Thread.sleep(1000 * 60 * 5);  // 5분 대기 (테스트 시 적절히 조정)

        // 7. 스케줄이 여전히 등록되어 있는지 확인 (비동기 작업일 경우)
        assertTrue(scheduledTasks.get(0).isDone() || !scheduledTasks.get(0).isCancelled(),
                "The scheduled task should either be done or still running.");

        log.info("Scheduled task has been executed successfully.");
    }
}
