package com.springcooler.sgma.studygroupapplicant.command.application.controller;

import com.springcooler.sgma.recruitmentboard.common.ResponseDTO;
import com.springcooler.sgma.studygroupapplicant.command.application.dto.StudyGroupApplicantCommandDTO;
import com.springcooler.sgma.studygroupapplicant.command.application.service.StudyGroupApplicantCommandService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/study-applicant")
@AllArgsConstructor
@RequiredArgsConstructor
public class StudyGroupApplicantCommandController {

    @Autowired
    private StudyGroupApplicantCommandService studyGroupApplicantService;

    @PostMapping
    @Operation(summary = "스터디 그룹 지원 신청")
    public ResponseDTO<?> applyApplicant(@RequestBody StudyGroupApplicantCommandDTO studyGroupApplicantCommandDTO){
        studyGroupApplicantService.applyStudyGroup(studyGroupApplicantCommandDTO);
        return ResponseDTO.ok(studyGroupApplicantCommandDTO);
    }

    @DeleteMapping("{userId}/{recruitmentBoardId}")
    @Operation(summary = "스터디 그룹 지원 신청취소")
    public ResponseDTO<?> deleteApplicant(@PathVariable("userId") Long userId, @PathVariable("recruitmentBoardId") Long recruitmentBoardId){
        studyGroupApplicantService.cancelStudyGroupApply(userId, recruitmentBoardId);
        return ResponseDTO.ok("지원 신청 취소");
    }

    @PostMapping("{userId}/{recruitmentBoardId}")
    @Operation(summary = "스터디 그룹 지원 승인")
    public ResponseDTO<?> approveApplicant(@PathVariable("userId") Long userId, @PathVariable("recruitmentBoardId") Long recruitmentBoardId) {
        studyGroupApplicantService.approveStudyGroupApplicant(userId, recruitmentBoardId);
        return ResponseDTO.ok("지원 승인 성공");
    }

    @PutMapping("{userId}/{recruitmentBoardId}")
    @Operation(summary = "스터디 그룹 지원 거절")
    public ResponseDTO<?> rejectApplicant(@PathVariable("userId") Long userId, @PathVariable("recruitmentBoardId") Long recruitmentBoardId) {
        studyGroupApplicantService.rejectStudyGroupApplicant(userId, recruitmentBoardId);
        return ResponseDTO.ok("지원 승인 거절");
    }
}
