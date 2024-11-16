package com.springcooler.sgma.studygroupapplicant.query.controller;

import com.springcooler.sgma.recruitmentboard.common.ResponseDTO;
import com.springcooler.sgma.studygroupapplicant.query.dto.StudyGroupApplicantDTO;
import com.springcooler.sgma.studygroupapplicant.query.service.StudyGroupApplicantService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("StudyGroupApplicantController")
@RequestMapping("/api/study-applicant")
public class StudyGroupApplicantController {
    private final StudyGroupApplicantService studyGroupApplicantService;

    @Autowired

    public StudyGroupApplicantController(StudyGroupApplicantService studyGroupApplicantService) {
        this.studyGroupApplicantService = studyGroupApplicantService;
    }

    @GetMapping("studygroup/{recruitmentBoardId}")
    @Operation(summary = "스터디 그룹별 지원 조회")
    public ResponseDTO<?> findStudyGroupApplicantByRecruitmentBoardId(@PathVariable("recruitmentBoardId") Long recruitmentBoardId) {
        List<StudyGroupApplicantDTO> studyGroupApplicantDTO =studyGroupApplicantService.findStudyGroupApplicantByRecruitmentBoardId(recruitmentBoardId);
        return ResponseDTO.ok(studyGroupApplicantDTO);
    }


    @GetMapping("user/{userId}")
    @Operation(summary = "회원별 지원 조회")
    public ResponseDTO<?> findStudyGroupApplicantByUserId(@PathVariable("userId") Long userId) {
        List<StudyGroupApplicantDTO> studyGroupApplicantDTO =studyGroupApplicantService.findStudyGroupApplicantByUserId(userId);
        return ResponseDTO.ok(studyGroupApplicantDTO);
    }
    @GetMapping("group/{groupId}")
    @Operation(summary = "그룹별 지원 조회")
    public ResponseDTO<?> findStudyGroupApplicantByGroupId(@PathVariable("groupId") Long groupId) {
        List<StudyGroupApplicantDTO> studyGroupApplicantDTO =studyGroupApplicantService.findStudyGroupApplicantByGroupId(groupId);
        return ResponseDTO.ok(studyGroupApplicantDTO);
    }
}
