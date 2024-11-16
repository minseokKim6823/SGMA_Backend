package com.springcooler.sgma.studygroupnotice.query.service;

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
    void testFindStudyGroupNoticesByGroupId(long groupId) {
        Assertions.assertDoesNotThrow(
                () -> {
                    List<StudyGroupNoticeDTO> studyGroupNotices =
                            studyGroupNoticeService.findStudyGroupNoticesByGroupId(groupId);
                    studyGroupNotices.forEach(x -> log.info(x.toString()));
                }
        );
    }

    @DisplayName("스터디그룹 공지사항 단건 조회(공지사항 아이디) 테스트")
    @ParameterizedTest
    @ValueSource(longs = 2L)
    void testFindStudyGroupNoticeByNoticeId(long noticeId) {
        Assertions.assertDoesNotThrow(
                () -> {
                    StudyGroupNoticeDTO studyGroupNotice =
                            studyGroupNoticeService.findStudyGroupNoticeByNoticeId(noticeId);
                    log.info(studyGroupNotice.toString());
                }
        );
    }
}
