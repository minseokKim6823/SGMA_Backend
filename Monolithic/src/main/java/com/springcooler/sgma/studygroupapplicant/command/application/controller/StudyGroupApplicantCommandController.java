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

    @PostMapping("apply")
    @Operation(summary = "스터디 그룹 지원 신청")
    public ResponseDTO<?> applyApplicant(@RequestBody StudyGroupApplicantCommandDTO studyGroupApplicantCommandDTO){
        studyGroupApplicantService.applyStudyGroup(studyGroupApplicantCommandDTO);
        return ResponseDTO.ok(studyGroupApplicantCommandDTO);
    }

    @DeleteMapping("delete/{userId}/{recruitmentBoardId}")
    @Operation(summary = "스터디 그룹 지원 신청취소")
    public ResponseDTO<?> deleteApplicant(@PathVariable Long userId, @PathVariable Long recruitmentBoardId){
        studyGroupApplicantService.cancelStudyGroupApply(userId, recruitmentBoardId);
        return ResponseDTO.ok("지원 신청 취소");
    }

    @PostMapping("approve/{userId}/{recruitmentBoardId}")
    @Operation(summary = "스터디 그룹 지원 승인")
    public ResponseDTO<?> approveApplicant(@PathVariable Long userId, @PathVariable Long recruitmentBoardId) {
        studyGroupApplicantService.approveStudyGroupApplicant(userId, recruitmentBoardId);
        return ResponseDTO.ok("지원 승인 성공");
    }

    @PutMapping("reject/{userId}/{recruitmentBoardId}")
    @Operation(summary = "스터디 그룹 지원 거절")
    public ResponseDTO<?> rejectApplicant(@PathVariable Long userId, @PathVariable Long recruitmentBoardId) {
        studyGroupApplicantService.rejectStudyGroupApplicant(userId, recruitmentBoardId);
        return ResponseDTO.ok("지원 승인 거절");
    }
}
