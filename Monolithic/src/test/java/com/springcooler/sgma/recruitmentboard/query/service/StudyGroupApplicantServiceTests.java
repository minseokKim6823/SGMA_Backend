package com.springcooler.sgma.recruitmentboard.query.service;


import com.springcooler.sgma.recruitmentboard.query.dto.RecruitmentBoardDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;



@SpringBootTest
class StudyGroupApplicantServiceTests {

    @Autowired
    RecruitmentBoardService studyGroupApplicantService;

    @DisplayName("스터디 그룹 지원 전체 조회 테스트")
    @Test
    void testStudyGroupRecruitment() {
        Assertions.assertDoesNotThrow(
                () -> {
                    List<RecruitmentBoardDTO> studyGroupApplicantDTOS = studyGroupApplicantService.studyGroupRecruitment();
                    studyGroupApplicantDTOS.forEach(System.out::println);
                }
        );
    }

    @DisplayName("스터디 그룹 지원 단 건 조회 테스트")
    @Test
    void testSelectStudyGroupApplicantById() {

        Long recruitmentBoardId = 1L;
        RecruitmentBoardDTO actualApplicant = studyGroupApplicantService.selectStudyGroupApplicantById(recruitmentBoardId);
        RecruitmentBoardDTO expectedApplicant = new RecruitmentBoardDTO();
        expectedApplicant.setRecruitmentBoardId(recruitmentBoardId);

        Assertions.assertEquals(actualApplicant.getRecruitmentBoardId(),expectedApplicant.getRecruitmentBoardId());
    }
}



