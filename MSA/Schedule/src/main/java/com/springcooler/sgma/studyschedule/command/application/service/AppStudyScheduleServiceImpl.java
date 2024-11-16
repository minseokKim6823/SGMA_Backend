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

    @Autowired
    public AppStudyScheduleServiceImpl(ModelMapper modelMapper,
                                       DomainStudyScheduleService domainStudyScheduleService,
                                       StudyScheduleRepository studyScheduleRepository) {
        this.modelMapper = modelMapper;
        this.domainStudyScheduleService = domainStudyScheduleService;
        this.studyScheduleRepository = studyScheduleRepository;
    }

    // 스터디 일정 생성
    @Transactional
    @Override
    public StudyScheduleDTO registStudySchedule(StudyScheduleDTO newStudySchedule) {
        if(!domainStudyScheduleService.isValidDTO(RestStatus.POST, newStudySchedule)) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        StudyScheduleDTO createStudySchedule = StudyScheduleDTO.builder()
                .title(newStudySchedule.getTitle())
                .content(newStudySchedule.getContent())
                .scheduleStartTime(newStudySchedule.getScheduleStartTime())
                .scheduleEndTime(newStudySchedule.getScheduleEndTime())
                .numParticipants(0)
                .activeStatus(StudyScheduleStatus.ACTIVE)
                .testStatus(newStudySchedule.getTestStatus())
                .testAverage(0.0)
                .testStandardDeviation(0.0)
                .groupId(newStudySchedule.getGroupId())
                .numProblemsPerParticipant(newStudySchedule.getNumProblemsPerParticipant())
                .build();


        if ("N".equalsIgnoreCase(newStudySchedule.getTestStatus())) {
            newStudySchedule.setNumProblemsPerParticipant(0);
        }

        StudySchedule schedule = modelMapper.map(createStudySchedule, StudySchedule.class);
        studyScheduleRepository.save(schedule);

        return modelMapper.map(schedule, StudyScheduleDTO.class);
    }

    // 스터디 일정 수정
    @Transactional
    @Override
    public StudyScheduleDTO modifyStudySchedule(StudyScheduleDTO modifyStudySchedule) {
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

        studyScheduleRepository.save(existingSchedule);
        return modelMapper.map(existingSchedule, StudyScheduleDTO.class);
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
}