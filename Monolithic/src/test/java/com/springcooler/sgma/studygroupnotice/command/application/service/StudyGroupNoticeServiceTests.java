package com.springcooler.sgma.studygroupnotice.command.application.service;

import com.springcooler.sgma.studygroupnotice.command.application.dto.StudyGroupNoticeDTO;
import com.springcooler.sgma.studygroupnotice.common.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional
class StudyGroupNoticeServiceTests {

    @Autowired
    private AppStudyGroupNoticeService studyGroupNoticeService;

    @DisplayName("스터디그룹 공지사항 생성 테스트")
    @Test
    void testRegistStudyGroupNotice() {
        //Given
        StudyGroupNoticeDTO newNotice = StudyGroupNoticeDTO.builder()
                .title("테스트용 제목")
                .content("테스트용 내용")
                .groupId(1L)
                .build();

        //When
        StudyGroupNoticeDTO notice = studyGroupNoticeService.registStudyGroupNotice(newNotice);
        if (notice != null) {
            log.info(notice.toString());
        }

        //Then
        Assertions.assertNotNull(notice);
    }

    @DisplayName("스터디그룹 공지사항 정보 수정 테스트")
    @Test
    void testModifyStudyGroupNotice() {
        //Given
        StudyGroupNoticeDTO modifyNotice = StudyGroupNoticeDTO.builder()
                .noticeId(1L)
                .title("바뀐 제목")
                .content("바뀐 내용")
                .build();

        //When
        StudyGroupNoticeDTO notice = studyGroupNoticeService.modifyStudyGroupNotice(modifyNotice);
        if (notice != null) {
            log.info(notice.toString());
        }

        //Then
        Assertions.assertNotNull(notice);
    }

    @DisplayName("스터디그룹 공지사항 삭제 테스트")
    @Test
    void testDeleteStudyGroupNotice() {
        //Given
        long noticeId = 1L;

        //When
        studyGroupNoticeService.deleteStudyGroupNotice(noticeId);
        log.info("DELETE SUCCESS");

        //Then
        Assertions.assertThrows(CommonException.class,
                () -> studyGroupNoticeService.deleteStudyGroupNotice(noticeId));
    }
}
