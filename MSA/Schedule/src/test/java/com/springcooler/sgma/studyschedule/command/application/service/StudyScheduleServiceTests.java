package com.springcooler.sgma.studyschedule.command.application.service;

import com.springcooler.sgma.studyschedule.common.exception.CommonException;
import com.springcooler.sgma.studyschedule.command.application.dto.StudyScheduleDTO;
import com.springcooler.sgma.studyschedule.command.domain.aggregate.StudySchedule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@SpringBootTest
@Transactional
class StudyScheduleServiceTests {

    @Autowired
    private AppStudyScheduleService studyScheduleService;

    @DisplayName("스터디 그룹 일정 생성 테스트")
    @Test
    void testRegistStudySchedule() {
        // Given
        StudyScheduleDTO newSchedule = new StudyScheduleDTO();
        newSchedule.setTitle("테스트 일정");
        newSchedule.setContent("테스트 일정 내용");
        newSchedule.setScheduleStartTime(Timestamp.valueOf("2024-09-01 10:00:00"));
        newSchedule.setScheduleEndTime(Timestamp.valueOf("2024-09-09 12:00:00"));
        newSchedule.setNumParticipants(0);
        newSchedule.setTestStatus("Y");
        newSchedule.setGroupId(1L);
        newSchedule.setNumProblemsPerParticipant(4);

        // When
        StudyScheduleDTO schedule = studyScheduleService.registStudySchedule(newSchedule);

        // Then
        Assertions.assertNotNull(schedule);
    }

    @DisplayName("스터디 그룹 일정 수정 테스트")
    @Test
    void testModifyStudySchedule() {
        // Given
        StudyScheduleDTO modifySchedule = new StudyScheduleDTO();
        modifySchedule.setScheduleId(1L);
        modifySchedule.setTitle("수정된 테스트 일정");
        modifySchedule.setContent("수정된 테스트 일정 내용");
        modifySchedule.setScheduleStartTime(Timestamp.valueOf("2024-09-06 10:00:00"));
        modifySchedule.setScheduleEndTime(Timestamp.valueOf("2024-09-19 12:00:00"));
        modifySchedule.setTestStatus("N");
        modifySchedule.setGroupId(1L);
        modifySchedule.setNumProblemsPerParticipant(4);

        // When
        StudyScheduleDTO updatedSchedule = studyScheduleService.modifyStudySchedule(modifySchedule);

        // Then
        Assertions.assertNotNull(updatedSchedule);
        Assertions.assertEquals("수정된 테스트 일정", updatedSchedule.getTitle());
    }

    @DisplayName("스터디 그룹 일정 삭제 테스트")
    @Test
    void testDeleteStudySchedule() {
        // Given
        Long scheduleId = 7L;

        // When
        studyScheduleService.deleteStudySchedule(scheduleId);
        System.out.println("DELETE SUCCESS");

        // Then
        Assertions.assertThrows(CommonException.class,
                () -> studyScheduleService.deleteStudySchedule(scheduleId));
    }
}
