package com.springcooler.sgma.studygroup.query.service;

import com.springcooler.sgma.studygroup.query.dto.MyStudyGroupDTO;
import com.springcooler.sgma.studygroup.query.dto.StudyGroupDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class StudyGroupServiceTests {

    @Autowired
    private StudyGroupService studyGroupService;

    @DisplayName("스터디그룹 전체 조회 테스트")
    @Test
    void testFindAllStudyGroupsByActiveStatus() {
        Assertions.assertDoesNotThrow(
            () -> {
                List<StudyGroupDTO> studyGroups = studyGroupService.findAllStudyGroups();
                studyGroups.forEach(x -> log.info(x.toString()));
            }
        );
    }

    @DisplayName("그룹장인 스터디그룹 조회 테스트")
    @ParameterizedTest
    @ValueSource(longs = 3L)
    void testFindStudyGroupsByOwnerId(Long ownerId) {
        Assertions.assertDoesNotThrow(
                () -> {
                    List<StudyGroupDTO> studyGroups = studyGroupService.findStudyGroupsByOwnerId(ownerId);
                    studyGroups.forEach(x -> log.info(x.toString()));
                }
        );
    }

    @DisplayName("그룹원인 스터디그룹 조회 테스트")
    @ParameterizedTest
    @ValueSource(longs = 1L)
    void testFindStudyGroupsByParticipantId(Long participantId) {
        Assertions.assertDoesNotThrow(
                () -> {
                    List<StudyGroupDTO> studyGroups = studyGroupService.findStudyGroupsByParticipantId(participantId);
                    studyGroups.forEach(x -> log.info(x.toString()));
                }
        );
    }

    @DisplayName("스터디그룹 카테고리별 조회 테스트")
    @ParameterizedTest
    @ValueSource(ints = 3)
    void testFindStudyGroupsByCategoryId(Integer categoryId) {
        Assertions.assertDoesNotThrow(
                () -> {
                    List<StudyGroupDTO> studyGroups = studyGroupService.findStudyGroupsByCategoryId(categoryId);
                    studyGroups.forEach(x -> log.info(x.toString()));
                }
        );
    }

    @DisplayName("스터디 그룹 단건 조회(그룹 아이디) 테스트")
    @ParameterizedTest
    @ValueSource(longs = 4L)
    void testFindStudyGroupByGroupId(Long groupId) {
        Assertions.assertDoesNotThrow(
                () -> {
                    StudyGroupDTO studyGroup = studyGroupService.findStudyGroupByGroupId(groupId);
                    log.info(studyGroup.toString());
                }
        );
    }

    @DisplayName("스터디 그룹 단건 조회(그룹 이름) 테스트")
    @ParameterizedTest
    @ValueSource(strings = "스프링쿨러")
    void testFindStudyGroupByGroupId(String groupName) {
        Assertions.assertDoesNotThrow(
                () -> {
                    StudyGroupDTO studyGroup = studyGroupService.findStudyGroupByGroupName(groupName);
                    log.info(studyGroup.toString());
                }
        );
    }

    @DisplayName("내가 가입한 스터디그룹 조회 테스트")
    @ParameterizedTest
    @ValueSource(longs = 1L)
    void testFindStudyGroupsByUserId(Long userId) {
        Assertions.assertDoesNotThrow(
                () -> {
                    List<MyStudyGroupDTO> studyGroups = studyGroupService.findStudyGroupsByUserId(userId);
                    studyGroups.forEach(x -> log.info(x.toString()));
                }
        );
    }

}
