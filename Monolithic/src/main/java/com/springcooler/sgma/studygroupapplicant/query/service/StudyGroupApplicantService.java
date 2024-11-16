package com.springcooler.sgma.studygroupapplicant.query.service;


import com.springcooler.sgma.studygroupapplicant.query.dto.StudyGroupApplicantDTO;
import com.springcooler.sgma.studygroupapplicant.query.repository.StudyGroupApplicantMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.springcooler.sgma.studygroupapplicant.common.Template.getSqlSession;

@Service
@Log4j2
public class StudyGroupApplicantService {
    private StudyGroupApplicantMapper studyGroupApplicantMapper;

    public List<StudyGroupApplicantDTO> selectStudyGroupApplicantByUserId(Long userId){
        SqlSession sqlSession =getSqlSession();
        StudyGroupApplicantMapper mapper=sqlSession.getMapper(StudyGroupApplicantMapper.class);
        List<StudyGroupApplicantDTO> studyGroupApplicant1 =mapper.studyGroupApplicant1(userId);
        System.out.println(studyGroupApplicant1);
        return studyGroupApplicant1;
    }

    public List<StudyGroupApplicantDTO> selectStudyGroupApplicantByRecruitmentBoardId(Long recruitBoardId){
        SqlSession sqlSession =getSqlSession();
        studyGroupApplicantMapper=sqlSession.getMapper(StudyGroupApplicantMapper.class);
        List<StudyGroupApplicantDTO> studyGroupApplicant2=studyGroupApplicantMapper.studyGroupApplicant2(recruitBoardId);
        System.out.println(studyGroupApplicant2);
        return studyGroupApplicant2;
    }

}
