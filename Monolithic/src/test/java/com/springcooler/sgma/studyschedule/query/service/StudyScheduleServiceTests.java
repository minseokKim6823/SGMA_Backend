package com.springcooler.sgma.studyschedule.query.service;

import com.springcooler.sgma.studyschedule.query.dto.StudyScheduleDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        Assertions.assertNotNull(ScheduleByScheduleId);
    }

    @DisplayName("스터디 그룹 일정 전체 조회 테스트")
    @Test
    void testFindStudyScheduleByGroupId() {
        // Given
        long groupId = 1L;

        // When
        List<StudyScheduleDTO> ScheduleByGroupId = studyScheduleService.findStudyScheduleByGroupId(groupId);

        // Then
        Assertions.assertNotNull(ScheduleByGroupId);
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
        Assertions.assertNotNull(SchedulesByPeriod);
    }

    @DisplayName("스터디 그룹 일정 시험 통계 조회 테스트")
    @Test
    void testFindStudyScheduleByStatistics() {
        // Given
        long scheduleId = 3L;

        // When
        StudyScheduleDTO ScheduleByStatistics = studyScheduleService.findStudyScheduleByStatistics(scheduleId);

        // Then
        Assertions.assertNotNull(ScheduleByStatistics);
    }
}