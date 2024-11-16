package com.springcooler.sgma.studyscheduleparticipant.command.application.service;

import com.springcooler.sgma.studyschedule.common.exception.CommonException;
import com.springcooler.sgma.studyschedule.common.exception.ErrorCode;
import com.springcooler.sgma.studyscheduleparticipant.command.application.dto.StudyScheduleParticipantDTO;
import com.springcooler.sgma.studyschedule.command.domain.aggregate.StudySchedule;
import com.springcooler.sgma.studyscheduleparticipant.command.domain.aggregate.RestStatus;
import com.springcooler.sgma.studyscheduleparticipant.command.domain.aggregate.StudyScheduleParticipant;
import com.springcooler.sgma.studyscheduleparticipant.command.domain.repository.StudyScheduleParticipantRepository;
import com.springcooler.sgma.studyschedule.command.domain.repository.StudyScheduleRepository;
import com.springcooler.sgma.studyscheduleparticipant.command.domain.service.DomainStudyScheduleParticipantService;
import com.springcooler.sgma.studyscheduleparticipant.command.infrastructure.service.InfraStudyScheduleParticipantService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
public class AppStudyScheduleParticipantServiceImpl implements AppStudyScheduleParticipantService {

    private final StudyScheduleParticipantRepository participantRepository;
    private final StudyScheduleRepository scheduleRepository;
    private final DomainStudyScheduleParticipantService domainStudyScheduleParticipantService;
    private final ModelMapper modelMapper;

    @Autowired
    public AppStudyScheduleParticipantServiceImpl(StudyScheduleParticipantRepository participantRepository,
                                                  StudyScheduleRepository scheduleRepository,
                                                  DomainStudyScheduleParticipantService domainStudyScheduleParticipantService,
                                                  ModelMapper modelMapper) {
        this.participantRepository = participantRepository;
        this.scheduleRepository = scheduleRepository;
        this.domainStudyScheduleParticipantService = domainStudyScheduleParticipantService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public StudyScheduleParticipant registStudyScheduleParticipant(StudyScheduleParticipantDTO newParticipant) {
        if (!domainStudyScheduleParticipantService.isValidDTO(RestStatus.POST, newParticipant)) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        StudySchedule schedule = scheduleRepository.findById(newParticipant.getScheduleId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_SCHEDULE));

        StudyScheduleParticipant participant = modelMapper.map(newParticipant, StudyScheduleParticipant.class);

        participantRepository.save(participant);
        schedule.setNumParticipants(schedule.getNumParticipants() + 1);
        scheduleRepository.save(schedule);

        return participant;
    }

    @Transactional
    @Override
    public void deleteStudyScheduleParticipant(Long scheduleId, Long memberId) {
        StudySchedule schedule =
                scheduleRepository.findById(scheduleId)
                        .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_SCHEDULE));

        StudyScheduleParticipant participant =
                participantRepository.findByScheduleIdAndMemberId(scheduleId, memberId)
                        .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_SCHEDULE_PARTICIPANT));

        participantRepository.delete(participant);

        schedule.setNumParticipants(schedule.getNumParticipants() - 1);
        scheduleRepository.save(schedule);
    }

    @Transactional
    @Override
    public void increaseNumSubmittedProblems(Long scheduleId, Long participantId) {
        StudyScheduleParticipant participant = participantRepository.findByScheduleIdAndParticipantId(scheduleId, participantId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_SCHEDULE_PARTICIPANT));

        participant.setNumSubmittedProblems(participant.getNumSubmittedProblems() + 1);
        log.debug("participant: {}", participant);
        participantRepository.save(participant);

        StudySchedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_SCHEDULE));

        if (participant.getNumSubmittedProblems().equals(schedule.getNumProblemsPerParticipant())) {
            participant.setSubmissionStatus("Y");
            participantRepository.save(participant);
        }

    }

    @Transactional
    @Override
    public void decreaseNumSubmittedProblems(Long scheduleId, Long participantId) {
        StudyScheduleParticipant participant = participantRepository.findByScheduleIdAndParticipantId(scheduleId, participantId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_SCHEDULE_PARTICIPANT));

        participant.setNumSubmittedProblems(participant.getNumSubmittedProblems() - 1);
        log.debug("participant: {}", participant);
        participantRepository.save(participant);

        StudySchedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_SCHEDULE));

        if (!Objects.equals(participant.getNumSubmittedProblems(), schedule.getNumProblemsPerParticipant())) {
            participant.setSubmissionStatus("N");
            participantRepository.save(participant);
        }
    }

    @Transactional
    @Override
    public void gradeSubmittedAnswersByParticipantId(long scheduleId, long participantId, double score) {
        log.debug("score: {}", score);
        StudyScheduleParticipant participant = participantRepository.findByScheduleIdAndParticipantId(scheduleId, participantId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_SCHEDULE_PARTICIPANT));

        participant.setTestScore(score * 100);
        participant.setTestPercentage(score * 100);
        log.debug("score: {}", score);

        participantRepository.save(participant);
    }
}
