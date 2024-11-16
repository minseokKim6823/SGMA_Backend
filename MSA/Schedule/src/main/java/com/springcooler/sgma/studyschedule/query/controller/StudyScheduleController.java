package com.springcooler.sgma.studyschedule.query.controller;

import com.springcooler.sgma.studyschedule.common.ResponseDTO;
import com.springcooler.sgma.studyschedule.query.dto.StudyScheduleDTO;
import com.springcooler.sgma.studyschedule.query.service.StudyScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController("queryStudyScheduleController")
@RequestMapping("/api/study-schedule")
public class StudyScheduleController {

    private final StudyScheduleService studyScheduleService;

    @Autowired
    public StudyScheduleController(StudyScheduleService studyScheduleService) {
        this.studyScheduleService = studyScheduleService;
    }

    // 스터디 그룹 일정 단건 조회
    @GetMapping("/schedule/{scheduleId}")
    public ResponseDTO<?> findStudyScheduleByScheduleId(@PathVariable Long scheduleId) {
        StudyScheduleDTO schedule = studyScheduleService.findStudyScheduleByScheduleId(scheduleId);
        return ResponseDTO.ok(schedule);
    }

    // 스터디 그룹 일정 전체 조회
    @GetMapping("/scheduleGroup/{groupId}")
    public ResponseDTO<?> findStudyScheduleByGroupId(@PathVariable long groupId) {
        List<StudyScheduleDTO> schedules = studyScheduleService.findStudyScheduleByGroupId(groupId);
        return ResponseDTO.ok(schedules);
    }

    // 스터디 그룹 일정 기간별 조회
    @GetMapping("/schedulesByPeriod/{groupId}/{startDate}/{endDate}")
    public ResponseDTO<?> findStudySchedulesByPeriod(@PathVariable long groupId, @PathVariable String startDate, @PathVariable String endDate) {
        List<StudyScheduleDTO> scheduleByPeriod = studyScheduleService.findStudySchedulesByPeriod(groupId, startDate, endDate);
        // 날짜 변환을 위해 SimpleDateFormat 사용
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        // 변환된 값을 저장할 리스트 생성
        List<Map<String, Object>> responseList = scheduleByPeriod.stream().map(schedule -> {
            Map<String, Object> scheduleMap = new HashMap<>();

            // Timestamp를 "yyyyMMdd" 형식의 문자열로 변환
            scheduleMap.put("scheduleStartTime", dateFormat.format(schedule.getScheduleStartTime()));
            scheduleMap.put("scheduleEndTime", dateFormat.format(schedule.getScheduleEndTime()));
            scheduleMap.put("schedule", schedule);

            return scheduleMap;
        }).collect(Collectors.toList());

        return ResponseDTO.ok(scheduleByPeriod);
    }

    // 스터디 그룹 일정 시험의 통계자료 조회
    @GetMapping("/scheduleStatistics/{scheduleId}")
    public ResponseDTO<?> findStudyScheduleByStatistics(@PathVariable long scheduleId) {
        StudyScheduleDTO statistics = studyScheduleService.findStudyScheduleByStatistics(scheduleId);
        return ResponseDTO.ok(statistics);
    }
}
