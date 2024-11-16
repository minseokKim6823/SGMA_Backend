package com.springcooler.sgma.studygroupmember.command.domain.service;

import com.springcooler.sgma.studygroupmember.command.domain.aggregate.RestStatus;
import com.springcooler.sgma.studygroupmember.command.application.dto.StudyGroupMemberDTO;
import com.springcooler.sgma.studygroupmember.command.domain.aggregate.StudyGroupMemberStatus;

public interface DomainStudyGroupMemberService {

    boolean isValidDTO(RestStatus restStatus, StudyGroupMemberDTO studyGroupMemberDTO);

    boolean isActive(StudyGroupMemberStatus memberStatus);

}
