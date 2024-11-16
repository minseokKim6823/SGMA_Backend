package com.springcooler.sgma.studyscheduleparticipant.command.application.service;

import com.springcooler.sgma.studyscheduleparticipant.query.dto.StudyScheduleParticipantDTO;
import com.springcooler.sgma.studyscheduleparticipant.query.service.StudyScheduleParticipantService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class StudyScheduleParticipantTests {

    @Autowired
    private StudyScheduleParticipantService studyScheduleParticipantService;

    @DisplayName("스터디 그룹 일정 참가자 조회 테스트")
    @Test
    void testFindStudyScheduleParticipant() {
        // Given
        long scheduleId = 1L;

        // When
        List<StudyScheduleParticipantDTO> participants = studyScheduleParticipantService.findStudyScheduleParticipant(scheduleId);

        // Then
        Assertions.assertNotNull(participants);
    }

    @DisplayName("스터디 그룹 일정 참가자의 시험 결과 조회 테스트")
    @Test
    void testFindStudyScheduleParticipantTestResult() {
        // Given
        long memberId = 1L;

        // When
        List<StudyScheduleParticipantDTO> testResults = studyScheduleParticipantService.findStudyScheduleParticipantTestResult(memberId);

        // Then
        Assertions.assertNotNull(testResults);
    }
}