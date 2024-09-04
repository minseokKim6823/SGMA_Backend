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
import com.springcooler.sgma.submittedanswer.command.infrastructure.service.InfraSubmittedAnswerService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class AppStudyScheduleParticipantServiceImpl implements AppStudyScheduleParticipantService {

    private final StudyScheduleParticipantRepository participantRepository;
    private final StudyScheduleRepository scheduleRepository;
    private final InfraSubmittedAnswerService infraSubmittedAnswerService;
    private final DomainStudyScheduleParticipantService domainStudyScheduleParticipantService;
    private final ModelMapper modelMapper;

    @Autowired
    public AppStudyScheduleParticipantServiceImpl(StudyScheduleParticipantRepository participantRepository,
                                                  StudyScheduleRepository scheduleRepository,
                                                  InfraSubmittedAnswerService infraSubmittedAnswerService,
                                                  DomainStudyScheduleParticipantService domainStudyScheduleParticipantService,
                                                  ModelMapper modelMapper) {
        this.participantRepository = participantRepository;
        this.scheduleRepository = scheduleRepository;
        this.infraSubmittedAnswerService = infraSubmittedAnswerService;
        this.domainStudyScheduleParticipantService = domainStudyScheduleParticipantService;
        this.modelMapper = modelMapper;
    }

    // 스터디 그룹 일정 참가
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

    // 스터디 그룹 일정 참가 취소
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

    // 출제 문제 수 및 상태 변경
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

    // 특정 참가자의 시험 점수와 백분율 계산
//    @Transactional
//    @Override
//    public void calculateAndUpdateParticipantScores(Long scheduleId) {
//        List<StudyScheduleParticipant> participants = participantRepository.findByScheduleId(scheduleId);
//
//        for (StudyScheduleParticipant participant : participants) {
//            if ("Y".equalsIgnoreCase(participant.getSubmissionStatus())) {
//                // 1. 특정 참가자가 제출한 답안 중 정답의 개수 가져오기
//                long correctAnswersCount = infraSubmittedAnswerService.getCorrectAnswersCount(participant.getParticipantId());
//
//                // 2. 같은 일정에 참가한 'Y' 상태인 참가자들의 제출한 문제 수 합산
//                List<StudyScheduleParticipant> participantsWithYStatus = participantRepository.findByScheduleIdAndSubmissionStatus(scheduleId, "Y");
//                int totalSubmittedProblems = participantsWithYStatus.stream()
//                        .mapToInt(StudyScheduleParticipant::getNumSubmittedProblems)
//                        .sum();
//
//                // 3. 점수 계산
//                double score = (totalSubmittedProblems > 0) ? (correctAnswersCount / (double) totalSubmittedProblems) * 100 : 0.0;
//                participant.setTestScore(score);
//                participant.setTestPercentage(score); // Assuming percentage is equal to score in this case
//                participantRepository.save(participant);
//            }
//        }
//    }
}