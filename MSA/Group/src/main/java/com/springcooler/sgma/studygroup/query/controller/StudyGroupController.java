package com.springcooler.sgma.studygroup.query.controller;

import com.springcooler.sgma.studygroup.common.ResponseDTO;
import com.springcooler.sgma.studygroup.query.dto.MyStudyGroupDTO;
import com.springcooler.sgma.studygroup.query.dto.StudyGroupDTO;
import com.springcooler.sgma.studygroup.query.service.StudyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("queryStudyGroupController")
@RequestMapping("/api/study-groups")
public class StudyGroupController {

    private final StudyGroupService studyGroupService;

    @Autowired
    public StudyGroupController(StudyGroupService studyGroupService) {
        this.studyGroupService = studyGroupService;
    }

    // 스터디 그룹 전체 조회
    @GetMapping
    public ResponseDTO<?> findAllStudyGroups() {
        List<StudyGroupDTO> studyGroups = studyGroupService.findAllStudyGroups();
        return ResponseDTO.ok(studyGroups);
    }

    // 그룹장인 스터디 그룹 조회
    @GetMapping("/owner/{ownerId}")
    public ResponseDTO<?> findStudyGroupsByOwnerId(@PathVariable("ownerId") Long ownerId) {
        List<StudyGroupDTO> studyGroups = studyGroupService.findStudyGroupsByOwnerId(ownerId);
        return ResponseDTO.ok(studyGroups);
    }

    // 그룹원인 스터디 그룹 조회
    @GetMapping("/participant/{participantId}")
    public ResponseDTO<?> findStudyGroupsByParticipantId(@PathVariable("participantId") Long participantId) {
        List<StudyGroupDTO> studyGroups = studyGroupService.findStudyGroupsByParticipantId(participantId);
        return ResponseDTO.ok(studyGroups);
    }

    // 스터디 그룹 카테고리별 조회
    @GetMapping("/category/{categoryId}")
    public ResponseDTO<?> findStudyGroupsByCategoryId(@PathVariable("categoryId") Integer categoryId) {
        List<StudyGroupDTO> studyGroups = studyGroupService.findStudyGroupsByCategoryId(categoryId);
        return ResponseDTO.ok(studyGroups);
    }

    // 스터디 그룹 단건 조회(그룹 아이디)
    @GetMapping("/{groupId}")
    public ResponseDTO<?> findStudyGroupByGroupId(@PathVariable("groupId") Long groupId) {
        StudyGroupDTO studyGroup = studyGroupService.findStudyGroupByGroupId(groupId);
        return ResponseDTO.ok(studyGroup);
    }

    // 스터디 그룹 단건 조회(그룹 이름)
    @GetMapping("/group-name/{groupName}")
    public ResponseDTO<?> findStudyGroupByGroupName(@PathVariable("groupName") String groupName) {
        StudyGroupDTO studyGroup = studyGroupService.findStudyGroupByGroupName(groupName);
        return ResponseDTO.ok(studyGroup);
    }

    // 내가 가입한 스터디 그룹 전체 조회
    @GetMapping("/user/{userId}")
    public ResponseDTO<?> findStudyGroupsByUserId(@PathVariable("userId") Long userId) {
        List<MyStudyGroupDTO> studyGroups = studyGroupService.findStudyGroupsByUserId(userId);
        return ResponseDTO.ok(studyGroups);
    }

}
