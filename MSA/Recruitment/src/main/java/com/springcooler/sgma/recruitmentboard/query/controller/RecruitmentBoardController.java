package com.springcooler.sgma.recruitmentboard.query.controller;

import com.springcooler.sgma.recruitmentboard.query.dto.PaginatedResponse;
import com.springcooler.sgma.recruitmentboard.query.dto.RecruitmentBoardDTO;
import com.springcooler.sgma.recruitmentboard.query.service.RecruitmentBoardService;


import com.springcooler.sgma.recruitmentboardcomment.common.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("queryRecruitmentBoardController")
@RequestMapping("/api/recruitment-board")
public class RecruitmentBoardController {

    private final RecruitmentBoardService recruitmentBoardService;

    @Autowired
    public RecruitmentBoardController(RecruitmentBoardService studyGroupApplicantService) {
        this.recruitmentBoardService = studyGroupApplicantService;
    }

//    @GetMapping("")
//    @Operation(summary = "모집글 전체 조회")
//    public ResponseDTO<?> findAllRecruitmentBoards() {
//        List<RecruitmentBoardDTO> recruitmentBoards = recruitmentBoardService.findAllRecruitmentBoards();
//        return ResponseDTO.ok(recruitmentBoards);
//    }

//    @GetMapping("/all")
//    @Operation(summary = "모집글 전체 조회")
//    public ResponseDTO<?> getRecruitmentBoards(
//            @RequestParam(defaultValue = "1") Integer page) {
//        final Integer size = 5;
//        PaginatedResponse<RecruitmentBoardDTO> response = recruitmentBoardService.findAllRecruitmentBoards(page, size);
//        return ResponseDTO.ok(response);
//    }
    @GetMapping("/all")
    @Operation(summary = "모집글 전체 조회")
    public ResponseDTO<?> getRecruitmentBoards() {
        List<RecruitmentBoardDTO> recruitmentBoard = recruitmentBoardService.findAllRecruitmentBoards();
        return ResponseDTO.ok(recruitmentBoard);
    }

    @GetMapping("board/{recruitmentBoardId}")
    @Operation(summary = "모집글 Id로 조회")
    public ResponseDTO<?> findRecruitmentBoardByBoardId(@PathVariable("recruitmentBoardId") Long recruitmentBoardId) {
        RecruitmentBoardDTO recruitmentBoard =
                recruitmentBoardService.findRecruitmentBoardByBoardId(recruitmentBoardId);
        return ResponseDTO.ok(recruitmentBoard);
    }

    @GetMapping("title/{recruitmentBoardTitle}")
    @Operation(summary = "모집글 제목으로 조회")
    public ResponseDTO<?> findRecruitmentBoardByBoardTitle(@PathVariable("recruitmentBoardTitle") String recruitmentBoardTitle) {
        List<RecruitmentBoardDTO> recruitmentBoard = recruitmentBoardService.findRecruitmentBoardsByTitle(recruitmentBoardTitle);
        return ResponseDTO.ok(recruitmentBoard);
    }

    @GetMapping("group/{groupId}")
    @Operation(summary = "그룹ID로 조회")
    public ResponseDTO<?> findRecruitmentBoardsByGroupId(@PathVariable("groupId") Long groupId) {
        List<RecruitmentBoardDTO> recruitmentBoard = recruitmentBoardService.findRecruitmentBoardsByGroupId(groupId);
        return ResponseDTO.ok(recruitmentBoard);
    }

    @GetMapping("category/{studyGroupCategoryId}")
    @Operation(summary = "카테고리ID로 조회")
    public ResponseDTO<?> findRecruitmentBoardsByCategory(@RequestParam(defaultValue = "1") Integer page, @PathVariable("studyGroupCategoryId") Integer studyGroupCategoryId) {
        final Integer size = 5;
        PaginatedResponse<RecruitmentBoardDTO> response = recruitmentBoardService.findRecruitmentBoardsByCategory(page, size, studyGroupCategoryId);
        return ResponseDTO.ok(response);
    }
    @GetMapping("mostliked")
    @Operation(summary = "좋아요 갯수 상위 3개 게시물 조회")
    public ResponseDTO<?> findTop3MostLikedPostsWithin7Days() {
        List<RecruitmentBoardDTO> response = recruitmentBoardService.findTop3MostLikedPostsWithin7Days();
        return ResponseDTO.ok(response);
    }
}

