package com.springcooler.sgma.recruitmentboard.command.application.controller;

import com.springcooler.sgma.recruitmentboard.command.application.dto.RecruitmentBoardCommandDTO;
import com.springcooler.sgma.recruitmentboard.command.application.service.RecruitmentBoardCommandService;
import com.springcooler.sgma.recruitmentboard.common.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/recruitment-board")
@AllArgsConstructor
@RequiredArgsConstructor
public class RecruitmentBoardCommandController {

    @Autowired
    private RecruitmentBoardCommandService recruitmentBoardCommandService;

    @PostMapping
    @Operation(summary = "모집글 작성")
    public ResponseDTO<?> createRecruitmentBoard(@RequestBody RecruitmentBoardCommandDTO studyGroupApplicantCommandDTO) {
        recruitmentBoardCommandService.createStudyGroupApplicant(studyGroupApplicantCommandDTO);
        return ResponseDTO.ok(studyGroupApplicantCommandDTO);
    }

    @PutMapping("/{recruitmentBoardId}")
    @Operation(summary = "모집글 수정")
    public ResponseDTO<?> updateRecruitmentBoard(@PathVariable("recruitmentBoardId") Long recruitmentBoardId, @RequestBody RecruitmentBoardCommandDTO studyGroupApplicantCommandDTO) {
        RecruitmentBoardCommandDTO updatedDto = recruitmentBoardCommandService.updateStudyGroupApplicant(recruitmentBoardId, studyGroupApplicantCommandDTO);
        return ResponseDTO.ok(updatedDto);
    }

    @DeleteMapping("/{recruitmentBoardId}")
    @Operation(summary = "모집글 삭제")
    public ResponseDTO<?> deleteRecruitmentBoard(@PathVariable("recruitmentBoardId") Long recruitmentBoardId) {
        boolean isDeleted = recruitmentBoardCommandService.deleteStudyGroupApplicant(recruitmentBoardId);
        return isDeleted ? ResponseDTO.ok("ok"): ResponseDTO.ok("notFound");
    }
}