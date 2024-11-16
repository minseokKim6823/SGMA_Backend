package com.springcooler.sgma.studyscheduleparticipant.command.application.service;

import com.springcooler.sgma.studyschedule.command.infrastructure.service.InfraStudyScheduleService;
import com.springcooler.sgma.studyschedule.common.exception.CommonException;
import com.springcooler.sgma.studyschedule.common.exception.ErrorCode;
import com.springcooler.sgma.studyscheduleparticipant.command.application.dto.StudyScheduleParticipantDTO;
import com.springcooler.sgma.studyschedule.command.domain.aggregate.StudySchedule;
import com.springcooler.sgma.studyscheduleparticipant.command.domain.aggregate.RestStatus;
import com.springcooler.sgma.studyscheduleparticipant.command.domain.aggregate.StudyScheduleParticipant;
import com.springcooler.sgma.studyscheduleparticipant.command.domain.repository.StudyScheduleParticipantRepository;
import com.springcooler.sgma.studyschedule.command.domain.repository.StudyScheduleRepository;
import com.springcooler.sgma.studyscheduleparticipant.command.domain.service.DomainStudyScheduleParticipantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class AppStudyScheduleParticipantServiceImpl implements AppStudyScheduleParticipantService {

    private final ModelMapper modelMapper;
    private final StudyScheduleRepository scheduleRepository;
    private final StudyScheduleParticipantRepository participantRepository;
    private final DomainStudyScheduleParticipantService domainStudyScheduleParticipantService;
    private final InfraStudyScheduleService infraStudyScheduleService;

    @Autowired
    public AppStudyScheduleParticipantServiceImpl(ModelMapper modelMapper,
                                                  StudyScheduleRepository scheduleRepository,
                                                  StudyScheduleParticipantRepository participantRepository,
                                                  DomainStudyScheduleParticipantService domainStudyScheduleParticipantService,
                                                  InfraStudyScheduleService infraStudyScheduleService) {
        this.modelMapper = modelMapper;
        this.scheduleRepository = scheduleRepository;
        this.participantRepository = participantRepository;
        this.domainStudyScheduleParticipantService = domainStudyScheduleParticipantService;
        this.infraStudyScheduleService = infraStudyScheduleService;
    }

    // 스터디 일정 참가
    @Transactional
    @Override
    public StudyScheduleParticipantDTO registStudyScheduleParticipant(StudyScheduleParticipantDTO newParticipant) {
        if (!domainStudyScheduleParticipantService.isValidDTO(RestStatus.POST, newParticipant)) {
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);
        }

        StudySchedule schedule = scheduleRepository.findById(newParticipant.getScheduleId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_SCHEDULE));

        StudyScheduleParticipantDTO tempParticipant = StudyScheduleParticipantDTO.builder()
                .scheduleId(newParticipant.getScheduleId())
                .memberId(newParticipant.getMemberId())
                .submissionStatus("N")
                .numSubmittedProblems(0)
                .testScore(0)
                .testPercentage(0.0)
                .build();

        StudyScheduleParticipant participant = modelMapper.map(tempParticipant, StudyScheduleParticipant.class);
        participantRepository.save(participant);
        schedule.setNumParticipants(schedule.getNumParticipants() + 1);
        scheduleRepository.save(schedule);

        return modelMapper.map(participant, StudyScheduleParticipantDTO.class);
    }

    // 스터디 일정 참가 취소
    @Transactional
    @Override
    public void deleteStudyScheduleParticipant(Long scheduleId, Long memberId) {
        StudySchedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_SCHEDULE));

        StudyScheduleParticipant participant = participantRepository.findByScheduleIdAndMemberId(scheduleId, memberId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_SCHEDULE_PARTICIPANT));

        participantRepository.delete(participant);

        schedule.setNumParticipants(schedule.getNumParticipants() - 1);
        scheduleRepository.save(schedule);
    }

    // 출제 문제 수 증가 및 출제 상태 변경
    @Transactional
    @Override
    public void increaseNumSubmittedProblems(Long scheduleId, Long participantId) {
        StudyScheduleParticipant participant = participantRepository.findByScheduleIdAndParticipantId(scheduleId, participantId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_SCHEDULE_PARTICIPANT));

        participant.setNumSubmittedProblems(participant.getNumSubmittedProblems() + 1);
        participantRepository.save(participant);

        StudySchedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_SCHEDULE));

        if (participant.getNumSubmittedProblems().equals(schedule.getNumProblemsPerParticipant())) {
            participant.setSubmissionStatus("Y");
            participantRepository.save(participant);
        }

    }

    // 출제 문제 수 감소 및 출제 상태 변경
    @Transactional
    @Override
    public void decreaseNumSubmittedProblems(Long scheduleId, Long participantId) {
        StudyScheduleParticipant participant = participantRepository.findByScheduleIdAndParticipantId(scheduleId, participantId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_SCHEDULE_PARTICIPANT));

        participant.setNumSubmittedProblems(participant.getNumSubmittedProblems() - 1);
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
    public void gradeSubmittedAnswersByParticipantId(Long scheduleId, Long participantId, Double score) {
        StudyScheduleParticipant participant = participantRepository.findByScheduleIdAndParticipantId(scheduleId, participantId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_STUDY_SCHEDULE_PARTICIPANT));

        // 소수점 둘째 자리까지 반올림
        double roundedScore = Math.round(score * 10000) / 100.0;

        participant.setTestScore((int) roundedScore);
        participant.setTestPercentage(roundedScore);

        participantRepository.save(participant);
        infraStudyScheduleService.updateScheduleWithParticipantScores(scheduleId);
    }
}