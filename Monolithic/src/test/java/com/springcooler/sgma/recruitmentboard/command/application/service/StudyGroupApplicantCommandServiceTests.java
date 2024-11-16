package com.springcooler.sgma.recruitmentboard.command.application.service;

import com.springcooler.sgma.recruitmentboard.command.application.dto.RecruitmentBoardCommandDTO;
import com.springcooler.sgma.recruitmentboard.command.domain.aggregate.BoardActiveStatus;
import com.springcooler.sgma.recruitmentboard.command.domain.aggregate.RecruitmentBoard;
import com.springcooler.sgma.recruitmentboard.command.domain.repository.RecruitmentBoardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;



@SpringBootTest
@Transactional
class StudyGroupApplicantCommandServiceTests {

    @Autowired
    private RecruitmentBoardCommandServiceImpl studyGroupApplicantCommandService;

    @Autowired
    private RecruitmentBoardRepository studyGroupApplicantRepository;


    @DisplayName("스터디 모집글 생성 테스트")
    @Test
    void testCreateStudyGroupApplicant() {
         RecruitmentBoardCommandDTO studyGroupApplicantCommandDTO = RecruitmentBoardCommandDTO.builder()
                .recruitmentBoardId(1L)
                .title("Test Title")
                .createdAt(Timestamp.valueOf("2023-09-01 10:00:00"))
                .updatedAt(Timestamp.valueOf("2023-09-01 10:00:00"))
                .recruitmentStartTime(Timestamp.valueOf("2023-09-01 10:00:00"))
                .recruitmentEndTime(Timestamp.valueOf("2023-09-30 18:00:00"))
                .groupId(100L)
                .activeStatus("ACTIVE")
                .studyGroupCategoryId(200)
                .likes(10)
                .build();

        RecruitmentBoard studyGroupApplicant =studyGroupApplicantCommandService.createStudyGroupApplicant(studyGroupApplicantCommandDTO);

        Assertions.assertNotNull(studyGroupApplicant);
    }

    @DisplayName("스터디 모집글 수정 테스트")
    @Test
    void testUpdateStudyGroupApplicant() {
        RecruitmentBoardCommandDTO studyGroupApplicantCommandDTO = RecruitmentBoardCommandDTO.builder()
                .recruitmentBoardId(1L)
                .title("Test Title")
                .createdAt(Timestamp.valueOf("2023-09-01 10:00:00"))
                .updatedAt(Timestamp.valueOf("2023-09-01 10:00:00"))
                .recruitmentStartTime(Timestamp.valueOf("2023-09-01 10:00:00"))
                .recruitmentEndTime(Timestamp.valueOf("2023-09-30 18:00:00"))
                .groupId(100L)
                .activeStatus("ACTIVE")
                .studyGroupCategoryId(200)
                .likes(10)
                .build();
        Optional<RecruitmentBoard> optionalApplicant = studyGroupApplicantRepository.findById(1L);
        if (optionalApplicant.isPresent()) {
            RecruitmentBoardCommandDTO studyGroupApplicantCommandDTO1= RecruitmentBoardCommandDTO.builder().title("테스트").build();
            RecruitmentBoardCommandDTO updatedDto = studyGroupApplicantCommandService.updateStudyGroupApplicant(1L, studyGroupApplicantCommandDTO1);
            Assertions.assertEquals(updatedDto.getTitle(),"테스트");
        }
    }

    @DisplayName("스터디 모집글 삭제 테스트")
    @Test
    void testDeleteStudyGroupApplicant() {
        boolean studyGroupApplicantCommandDto=false;
        RecruitmentBoardCommandDTO studyGroupApplicantCommandDTO = RecruitmentBoardCommandDTO.builder()
                .recruitmentBoardId(1L)
                .title("Test Title")
                .createdAt(Timestamp.valueOf("2023-09-01 10:00:00"))
                .updatedAt(Timestamp.valueOf("2023-09-01 10:00:00"))
                .recruitmentStartTime(Timestamp.valueOf("2023-09-01 10:00:00"))
                .recruitmentEndTime(Timestamp.valueOf("2023-09-30 18:00:00"))
                .groupId(100L)
                .activeStatus("ACTIVE")
                .studyGroupCategoryId(200)
                .likes(10)
                .build();
        Optional<RecruitmentBoard> optionalApplicant = studyGroupApplicantRepository.findById(1L);
        if (optionalApplicant.isPresent()) {
            studyGroupApplicantCommandService.deleteStudyGroupApplicant(1L);
            studyGroupApplicantCommandDto=true;
        }
        if(studyGroupApplicantCommandDto){
            Assertions.assertEquals(optionalApplicant.get().getActiveStatus(), BoardActiveStatus.DELETED);
        }

    }
}
