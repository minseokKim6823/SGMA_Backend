package com.springcooler.sgma.studyschedule.query.service;

import com.springcooler.sgma.studyschedule.query.dto.StudyScheduleDTO;
import com.springcooler.sgma.studyschedule.query.dto.StudyScheduleParticipantVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
@Slf4j
@SpringBootTest
@Transactional
class StudyScheduleServiceTests {

    @Autowired
    private StudyScheduleService studyScheduleService;

    @DisplayName("스터디 그룹 일정 단건 조회 테스트")
    @Test
    void testFindStudyScheduleByScheduleId() {
        // Given
        long scheduleId = 1L;

        // When
        StudyScheduleDTO ScheduleByScheduleId = studyScheduleService.findStudyScheduleByScheduleId(scheduleId);

        // Then
        assertNotNull(ScheduleByScheduleId);
    }

    @DisplayName("스터디 그룹 일정 전체 조회 테스트")
    @Test
    void testFindStudyScheduleByGroupId() {
        // Given
        long groupId = 1L;

        // When
        List<StudyScheduleDTO> ScheduleByGroupId = studyScheduleService.findStudyScheduleByGroupId(groupId);

        // Then
        assertNotNull(ScheduleByGroupId);
    }

    @DisplayName("스터디 그룹 일정 기간별 조회 테스트")
    @Test
    void testFindStudySchedulesByPeriod() {
        // Given
        long groupId = 1L;
        String startDate = "2024-09-01";
        String endDate = "2024-09-30";

        // When
        List<StudyScheduleDTO> SchedulesByPeriod = studyScheduleService.findStudySchedulesByPeriod(groupId, startDate, endDate);

        // Then
        assertNotNull(SchedulesByPeriod);
    }

    @DisplayName("스터디 그룹 일정 시험 통계 조회 테스트")
    @Test
    void testFindStudyScheduleByStatistics() {
        // Given
        long scheduleId = 3L;

        // When
        StudyScheduleDTO ScheduleByStatistics = studyScheduleService.findStudyScheduleByStatistics(scheduleId);

        // Then
        assertNotNull(ScheduleByStatistics);
    }

    @DisplayName("스터디 일정ID로 참여자 목록 조회 테스트")
    @Test
    void testFindParticipantsByScheduleId(){

        // given
        long scheduleId = 3L;

        // when
        StudyScheduleParticipantVO participantVO = studyScheduleService.findParticipantsByScheduleId(scheduleId);

        // then
        assertNotNull(participantVO);
        log.info("scheduleId: {}", participantVO.getScheduleId());
        participantVO.getParticipants().forEach(participant -> {
            log.info("participant: {}", participant);
        });
    }


}