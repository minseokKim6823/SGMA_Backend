package com.springcooler.sgma.studygroupapplicant.query.service;


import com.springcooler.sgma.studygroupapplicant.common.exception.CommonException;
import com.springcooler.sgma.studygroupapplicant.common.exception.ErrorCode;
import com.springcooler.sgma.studygroupapplicant.query.dto.StudyGroupApplicantDTO;
import com.springcooler.sgma.studygroupapplicant.query.repository.StudyGroupApplicantMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class StudyGroupApplicantService {

    private final StudyGroupApplicantMapper studyGroupApplicantMapper;

    @Autowired
    public StudyGroupApplicantService(StudyGroupApplicantMapper studyGroupApplicantMapper) {
        this.studyGroupApplicantMapper = studyGroupApplicantMapper;
    }

    public List<StudyGroupApplicantDTO> findStudyGroupApplicantByUserId(Long userId){
        List<StudyGroupApplicantDTO> applicant = studyGroupApplicantMapper.findStudyGroupApplicantByUserId(userId);
        if(applicant == null || applicant.isEmpty()){
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_APPLICANT);
        }
        return applicant;
    }

    public List<StudyGroupApplicantDTO> findStudyGroupApplicantByRecruitmentBoardId(Long recruitBoardId){
        List<StudyGroupApplicantDTO> applicant =
                studyGroupApplicantMapper.findStudyGroupApplicantByRecruitmentBoardId(recruitBoardId);
        if(applicant == null || applicant.isEmpty()){
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_APPLICANT);
        }
        return applicant;
    }

    public List<StudyGroupApplicantDTO> findStudyGroupApplicantByGroupId(Long groupId){
        List<StudyGroupApplicantDTO> applicant =
                studyGroupApplicantMapper.findStudyGroupApplicantByGroupId(groupId);
        if(applicant == null || applicant.isEmpty()){
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_APPLICANT);
        }
        return applicant;
    }

}
