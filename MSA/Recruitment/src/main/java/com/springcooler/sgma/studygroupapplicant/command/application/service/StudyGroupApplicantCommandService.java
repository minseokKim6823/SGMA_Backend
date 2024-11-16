package com.springcooler.sgma.studygroupapplicant.command.application.service;

import com.springcooler.sgma.studygroupapplicant.command.application.dto.StudyGroupApplicantCommandDTO;
import com.springcooler.sgma.studygroupapplicant.command.domain.aggregate.StudyGroupApplicant;
import org.springframework.transaction.annotation.Transactional;


public interface StudyGroupApplicantCommandService {

    // 스터디 그룹 신청

    StudyGroupApplicant applyStudyGroup(StudyGroupApplicantCommandDTO studyGroupApplicantCommandDTO);

    // 스터디 그룹 신청 취소

    void cancelStudyGroupApply(long userId, long groupId);

    // 스터디 그룹 신청 승인
    void approveStudyGroupApplicant(long userId, long groupId);

    // 스터디 그룹 신청 거절
    void rejectStudyGroupApplicant(long userId, long groupId);

}
