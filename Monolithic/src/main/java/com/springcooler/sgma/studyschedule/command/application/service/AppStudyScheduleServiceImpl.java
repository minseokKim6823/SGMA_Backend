package com.springcooler.sgma.studyschedule.command.application.service;

import com.springcooler.sgma.studyschedule.command.application.dto.StudyScheduleDTO;
import com.springcooler.sgma.studyschedule.command.domain.aggregate.RestStatus;
import com.springcooler.sgma.studyschedule.command.domain.aggregate.StudySchedule;
import com.springcooler.sgma.studyschedule.command.domain.aggregate.StudyScheduleStatus;
import com.springcooler.sgma.studyschedule.command.domain.repository.StudyScheduleRepository;
import com.springcooler.sgma.studyschedule.command.domain.service.DomainStudyScheduleService;
import com.springcooler.sgma.studyschedule.common.exception.CommonException;
import com.springcooler.sgma.studyschedule.common.exception.ErrorCode;
import com.springcooler.sgma.studyscheduleparticipant.command.domain.aggregate.StudyScheduleParticipant;
import com.springcooler.sgma.studyscheduleparticipant.command.domain.repository.StudyScheduleParticipantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppStudyScheduleServiceImpl implements AppStudyScheduleService {
    private final ModelMapper modelMapper;
    private final DomainStudyScheduleService domainStudyScheduleService;
    private final StudyScheduleRepository studyScheduleRepository;
    private final StudyScheduleParticipantRepository participantRepository;

    @Autowired
    public AppStudyScheduleServiceImpl(ModelMapper modelMapper,
                                       DomainStudyScheduleService domainStudyScheduleService,
                                       StudyScheduleRepository studyScheduleRepository,
                                       StudyScheduleParticipantRepository participantRepository) {
        this.modelMapper = modelMapper;
        this.domainStudyScheduleService = domainStudyScheduleService;
        this.studyScheduleRepository = studyScheduleRepository;
        this.participantRepository = participantRepository;
    }

    // 스터디 일정 생성
    @Transactional
    @Override
    public StudySchedule registStudySchedule(StudyScheduleDTO newStudySchedule) {
        if(!domainStudyScheduleService.isValidDTO(RestStatus.POST, newStudySchedule)) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        if ("N".equalsIgnoreCase(newStudySchedule.getTestStatus())) {
            newStudySchedule.setNumProblemsPerParticipant(0);
        }

        newStudySchedule.setActiveStatus(StudyScheduleStatus.ACTIVE.name());

        return studyScheduleRepository.save(modelMapper.map(newStudySchedule, StudySchedule.class));
    }

    // 스터디 일정 수정
    @Transactional
    @Override
    public StudySchedule modifyStudySchedule(StudyScheduleDTO modifyStudySchedule) {
        if(!domainStudyScheduleService.isValidDTO(RestStatus.POST, modifyStudySchedule)) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        StudySchedule existingSchedule =
                studyScheduleRepository.findById(modifyStudySchedule.getScheduleId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_SCHEDULE));

        existingSchedule.setTitle(modifyStudySchedule.getTitle());
        existingSchedule.setContent(modifyStudySchedule.getContent());
        existingSchedule.setScheduleStartTime(modifyStudySchedule.getScheduleStartTime());
        existingSchedule.setScheduleEndTime(modifyStudySchedule.getScheduleEndTime());
        existingSchedule.setTestStatus(modifyStudySchedule.getTestStatus());
        existingSchedule.setNumProblemsPerParticipant(modifyStudySchedule.getNumProblemsPerParticipant());

        if ("N".equalsIgnoreCase(modifyStudySchedule.getTestStatus())) {
            existingSchedule.setNumProblemsPerParticipant(0);
        }
        return studyScheduleRepository.save(existingSchedule);
    }

    // 스터디 일정 삭제
    @Transactional
    @Override
    public void deleteStudySchedule(Long scheduleId) {
        StudySchedule deleteSchedule =
                studyScheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new CommonException((ErrorCode.NOT_FOUND_STUDY_SCHEDULE)));

        if (!domainStudyScheduleService.isActive(deleteSchedule.getActiveStatus())) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_SCHEDULE);
        }

        deleteSchedule.setActiveStatus(StudyScheduleStatus.INACTIVE);
        studyScheduleRepository.save(deleteSchedule);
    }

    // 일정에 따른 참가자들의 시험 평균 및 표준편차 계산 및 업데이트
    @Transactional
    @Override
    public StudySchedule updateScheduleWithParticipantScores(Long scheduleId) {
        List<StudyScheduleParticipant> participants = participantRepository.findByScheduleId(scheduleId);

        List<Double> scores = participants.stream()
                .map(StudyScheduleParticipant::getTestScore)
                .toList();

        if (scores.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_SCHEDULE);
        }

        double average = scores.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);

        double variance = scores.stream()
                .mapToDouble(score -> Math.pow(score - average, 2))
                .average()
                .orElse(0.0);
        double standardDeviation = Math.sqrt(variance);

        StudySchedule schedule = studyScheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new CommonException((ErrorCode.NOT_FOUND_STUDY_SCHEDULE)));

        schedule.setTestAverage(average);
        schedule.setTestStandardDeviation(standardDeviation);
        studyScheduleRepository.save(schedule);
        return schedule;
    }
}
