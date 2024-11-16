package com.springcooler.sgma.studyschedule.scheduler;

import com.springcooler.sgma.studyschedule.command.domain.aggregate.StudySchedule;
import com.springcooler.sgma.studyschedule.command.domain.repository.StudyScheduleRepository;
import com.springcooler.sgma.studyschedule.query.dto.StudyScheduleParticipantVO;
import com.springcooler.sgma.studyschedule.query.service.StudyScheduleServiceImpl;
import com.springcooler.sgma.studyscheduleparticipant.command.infrastructure.service.InfraStudyScheduleParticipantService;
import com.springcooler.sgma.submittedanswer.command.infrastructure.service.InfraSubmittedAnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
@Slf4j
@Service
public class ScheduledTaskService {

    private final TrackingThreadPoolTaskScheduler trackingThreadPoolTaskScheduler;
    private final StudyScheduleRepository studyScheduleRepository;
    private final StudyScheduleServiceImpl studyScheduleService;
    private final InfraStudyScheduleParticipantService infraStudyScheduleParticipantService;
    @Autowired
    public ScheduledTaskService(TrackingThreadPoolTaskScheduler trackingThreadPoolTaskScheduler
                                , StudyScheduleRepository studyScheduleRepository
                                , StudyScheduleServiceImpl studyScheduleServiceImpl
                                , InfraStudyScheduleParticipantService infraStudyScheduleParticipantService){
        this.trackingThreadPoolTaskScheduler = trackingThreadPoolTaskScheduler;
        this.studyScheduleRepository = studyScheduleRepository;
        this.studyScheduleService = studyScheduleServiceImpl;
        this.infraStudyScheduleParticipantService = infraStudyScheduleParticipantService;


    }

@Scheduled(cron = "0 */10 * * * *")
    public void updateScheduledTasks(){
//        now()로 수정 예정
        Timestamp startTime = Timestamp.from(Instant.now());

        Timestamp endTime = Timestamp.valueOf(startTime.toLocalDateTime().plusHours(1));

        List<StudySchedule> scheduledTasks = studyScheduleRepository.findByScheduleEndTimeBetween(startTime, endTime);
        scheduledTasks.forEach(scheduledTask -> {
            StudyScheduleParticipantVO scheduleParticipantVO = studyScheduleService.findParticipantsByScheduleId(scheduledTask.getScheduleId());
            long scheduleId = scheduledTask.getScheduleId();
            scheduleParticipantVO.getParticipants().forEach(participantId->{
                scheduleTaskAtTimestamp(scheduledTask.getScheduleEndTime(), () -> infraStudyScheduleParticipantService.gradeAndUpdateParticipantScore(scheduleId, participantId));


            });


        });
    }
//    TODO: 가져온 작업들을 taskScheduler에 등록
    public ScheduledFuture<?> scheduleTaskAtTimestamp(Timestamp timestamp, Runnable task) {
        Instant date = timestamp.toInstant();
        return trackingThreadPoolTaskScheduler.schedule(task, date);
    }

}
