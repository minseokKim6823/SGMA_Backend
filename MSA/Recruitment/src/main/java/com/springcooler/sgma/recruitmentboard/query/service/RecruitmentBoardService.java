package com.springcooler.sgma.recruitmentboard.query.service;

import com.springcooler.sgma.recruitmentboard.common.exception.CommonException;
import com.springcooler.sgma.recruitmentboard.common.exception.ErrorCode;
import com.springcooler.sgma.recruitmentboard.query.dto.PaginatedResponse;
import com.springcooler.sgma.recruitmentboard.query.dto.RecruitmentBoardDTO;
import com.springcooler.sgma.recruitmentboard.query.repository.RecruitmentBoardMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class RecruitmentBoardService {

    private final RecruitmentBoardMapper recruitmentBoardMapper;

    @Autowired
    public RecruitmentBoardService(RecruitmentBoardMapper recruitmentBoardMapper) {
        this.recruitmentBoardMapper = recruitmentBoardMapper;
    }

    public List<RecruitmentBoardDTO> findAllRecruitmentBoards() {
        List<RecruitmentBoardDTO> recruitmentBoard = recruitmentBoardMapper.findAllRecruitmentBoards();
        if(recruitmentBoard == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_RECRUITMENT_BOARD);
        }
        return recruitmentBoard;
    }

    public RecruitmentBoardDTO findRecruitmentBoardByBoardId(Long recruitmentBoardId){
        RecruitmentBoardDTO recruitmentBoard =
                recruitmentBoardMapper.findRecruitmentBoardByBoardId(recruitmentBoardId);
        if(recruitmentBoard == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_RECRUITMENT_BOARD);
        }
        return recruitmentBoard;
    }

    public List<RecruitmentBoardDTO> findRecruitmentBoardsByTitle(String title){
        List<RecruitmentBoardDTO> recruitmentBoards = recruitmentBoardMapper.findRecruitmentBoardsByTitle(title);
        if(recruitmentBoards == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_RECRUITMENT_BOARD);
        }
        return recruitmentBoards;
    }

    public List<RecruitmentBoardDTO> findRecruitmentBoardsByGroupId(Long groupId){
        List<RecruitmentBoardDTO> recruitmentBoards= recruitmentBoardMapper.findRecruitmentBoardsByGroupId(groupId);
        if(recruitmentBoards == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_RECRUITMENT_BOARD);
        }
        return recruitmentBoards;
    }

    public PaginatedResponse<RecruitmentBoardDTO> findRecruitmentBoardsByCategory(Integer page, Integer size, Integer studyGroupCategoryId) {
        List<RecruitmentBoardDTO> recruitmentBoards = recruitmentBoardMapper.findRecruitmentBoardsByCategory(studyGroupCategoryId);
        long totalElements = recruitmentBoards.size();
        int totalPages = (int) Math.ceil((double) totalElements / size);

        // 페이징 처리
        int start = (page - 1) * size;
        int end = Math.min(start + size, recruitmentBoards.size());
        List<RecruitmentBoardDTO> paginatedBoards = recruitmentBoards.subList(start, end);

        PaginatedResponse<RecruitmentBoardDTO> response = new PaginatedResponse<>();
        response.setData(paginatedBoards);
        response.setTotalPages(totalPages);
        response.setTotalElements(totalElements);

        return response;
    }

    public List<RecruitmentBoardDTO> findTop3MostLikedPostsWithin7Days(){
        List<RecruitmentBoardDTO> recruitmentBoards= recruitmentBoardMapper.findTop3MostLikedPostsWithin7Days();
        if(recruitmentBoards == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_RECRUITMENT_BOARD);
        }
        return recruitmentBoards;
    }
}
