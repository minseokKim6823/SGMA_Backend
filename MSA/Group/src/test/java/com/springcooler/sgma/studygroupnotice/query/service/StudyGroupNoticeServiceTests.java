package com.springcooler.sgma.studygroupnotice.query.service;

import com.springcooler.sgma.studygroupnotice.query.dto.PageDTO;
import com.springcooler.sgma.studygroupnotice.query.dto.StudyGroupNoticeDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class StudyGroupNoticeServiceTests {

    @Autowired
    private StudyGroupNoticeService studyGroupNoticeService;

    @DisplayName("스터디그룹 공지사항 전체 조회(스터디그룹 아이디) 테스트")
    @ParameterizedTest
    @ValueSource(longs = 1L)
    void testFindStudyGroupNoticesByGroupId(Long groupId) {
        Assertions.assertDoesNotThrow(
                () -> {
                    PageDTO<StudyGroupNoticeDTO> page =
                            studyGroupNoticeService.findStudyGroupNoticesByGroupId(groupId, 1);
                    page.getElements().forEach(x -> log.info(x.toString()));
                }
        );
    }

    @DisplayName("스터디그룹 공지사항 단건 조회(공지사항 아이디) 테스트")
    @ParameterizedTest
    @ValueSource(longs = 2L)
    void testFindStudyGroupNoticeByNoticeId(Long noticeId) {
        Assertions.assertDoesNotThrow(
                () -> {
                    StudyGroupNoticeDTO studyGroupNotice =
                            studyGroupNoticeService.findStudyGroupNoticeByNoticeId(noticeId);
                    log.info(studyGroupNotice.toString());
                }
        );
    }

    @DisplayName("스터디그룹 공지사항 제목으로 조회 테스트")
    @ParameterizedTest
    @ValueSource(strings = "공")
    void testFindStudyGroupNoticesByTitle(String title) {
        Assertions.assertDoesNotThrow(
                () -> {
                    PageDTO<StudyGroupNoticeDTO> page =
                            studyGroupNoticeService.findStudyGroupNoticesByTitle(1L, title, 1);
                    page.getElements().forEach(x -> log.info(x.toString()));
                }
        );
    }

    @DisplayName("스터디그룹 공지사항 내용으로 조회 테스트")
    @ParameterizedTest
    @ValueSource(strings = "사")
    void testFindStudyGroupNoticesByContent(String content) {
        Assertions.assertDoesNotThrow(
                () -> {
                    PageDTO<StudyGroupNoticeDTO> page =
                            studyGroupNoticeService.findStudyGroupNoticesByContent(1L, content, 1);
                    page.getElements().forEach(x -> log.info(x.toString()));
                }
        );
    }

}
