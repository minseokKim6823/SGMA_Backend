package com.springcooler.sgma.studygroupmember.command.application.service;

import com.springcooler.sgma.studygroupmember.command.application.dto.StudyGroupMemberDTO;
import com.springcooler.sgma.studygroupmember.command.domain.aggregate.GroupRole;
import com.springcooler.sgma.studygroupmember.common.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@SpringBootTest
@Transactional
class StudyGroupMemberServiceTests {

    @Autowired
    private AppStudyGroupMemberService studyGroupMemberService;

    @DisplayName("스터디 그룹원 추가 테스트")
    @Test
    void testSaveStudyGroupMember() {
        //Given
        StudyGroupMemberDTO newMember = StudyGroupMemberDTO.builder()
                .userId(1L)
                .groupId(5L)
                .build();

        //When
        StudyGroupMemberDTO member = studyGroupMemberService.registStudyGroupMember(newMember);
        if (member != null) {
            log.info(member.toString());
        }

        //Then
        Assertions.assertNotNull(member);
    }

    @DisplayName("스터디 그룹원 정보 수정 테스트")
    @Test
    void testModifyStudyGroupMember() {
        //Given
        String dateTimeString = "2023-09-07 21:30:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, formatter);

        StudyGroupMemberDTO modifyMember = StudyGroupMemberDTO.builder()
                .memberId(3L)
                .memberEnrolledAt(localDateTime)
                .userId(3L)
                .groupId(5L)
                .groupRole(GroupRole.ROLE_OWNER)
                .build();

        //When
        StudyGroupMemberDTO member = studyGroupMemberService.modifyStudyGroupMember(modifyMember);
        if (member != null) {
            log.info(member.toString());
        }

        //Then
        Assertions.assertNotNull(member);
    }

    @DisplayName("스터디 그룹원 삭제 테스트")
    @Test
    void testDeleteStudyGroupMember() {
        //Given
        long memberId = 6L;

        //When
        studyGroupMemberService.deleteStudyGroupMember(memberId);
        log.info("DELETE SUCCESS");

        //Then
        Assertions.assertThrows(CommonException.class,
                () -> studyGroupMemberService.deleteStudyGroupMember(memberId));
    }
}
