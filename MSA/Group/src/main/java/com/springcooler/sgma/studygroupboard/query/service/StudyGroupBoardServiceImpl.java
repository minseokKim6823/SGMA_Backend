package com.springcooler.sgma.studygroupboard.query.service;

import com.springcooler.sgma.studygroupboard.common.exception.CommonException;
import com.springcooler.sgma.studygroupboard.common.exception.ErrorCode;
import com.springcooler.sgma.studygroupboard.query.dto.PageDTO;
import com.springcooler.sgma.studygroupboard.query.dto.StudyGroupBoardDTO;
import com.springcooler.sgma.studygroupboard.query.repository.StudyGroupBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyGroupBoardServiceImpl implements StudyGroupBoardService {

    private final Integer PAGE_SIZE = 10; // 페이지 숫자 개수
    private final Integer ELEMENTS_PER_PAGE = 10; // 한 페이지 당 요소 개수

    private final StudyGroupBoardMapper studyGroupBoardMapper;

    @Autowired
    public StudyGroupBoardServiceImpl(StudyGroupBoardMapper studyGroupBoardMapper) {
        this.studyGroupBoardMapper = studyGroupBoardMapper;
    }

    // 게시글 그룹별 조회
    @Override
    public PageDTO<StudyGroupBoardDTO> findStudyGroupBoardsByGroupId(Long groupId, Integer pageNo) {
        // 페이지 번호 유효성 검사
        if(pageNo == null || pageNo < 1) {
            throw new CommonException(ErrorCode.INVALID_PARAMETER_FORMAT);
        }

        // 그룹별 전체 게시글 수 조회
        Integer totalElements = studyGroupBoardMapper.getTotalElementsByGroupId(groupId);
        if(totalElements == null || totalElements < 1) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_BOARD);
        }

        // 현재 페이지 게시글 조회
        Integer offset = (pageNo - 1) * ELEMENTS_PER_PAGE;
        List<StudyGroupBoardDTO> boards = studyGroupBoardMapper.findStudyGroupBoardsByGroupId(groupId, ELEMENTS_PER_PAGE, offset);
        if(boards == null || boards.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_BOARD);
        }

        return new PageDTO<>(boards, pageNo, PAGE_SIZE, ELEMENTS_PER_PAGE, totalElements);
    }

    // 게시글 그룹원별 조회
    @Override
    public PageDTO<StudyGroupBoardDTO> findStudyGroupBoardsByMemberId(Long memberId, Integer pageNo) {
        // 페이지 번호 유효성 검사
        if(pageNo == null || pageNo < 1) {
            throw new CommonException(ErrorCode.INVALID_PARAMETER_FORMAT);
        }

        // 그룹원별 전체 게시글 수 조회
        Integer totalElements = studyGroupBoardMapper.getTotalElementsByMemberId(memberId);
        if(totalElements == null || totalElements < 1) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_BOARD);
        }

        // 현재 페이지 게시글 조회
        Integer offset = (pageNo - 1) * ELEMENTS_PER_PAGE;
        List<StudyGroupBoardDTO> boards = studyGroupBoardMapper.findStudyGroupBoardsByMemberId(memberId, ELEMENTS_PER_PAGE, offset);
        if(boards == null || boards.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_BOARD);
        }

        return new PageDTO<>(boards, pageNo, PAGE_SIZE, ELEMENTS_PER_PAGE, totalElements);
    }

    // 게시글 제목으로 조회
    @Override
    public PageDTO<StudyGroupBoardDTO> findStudyGroupBoardsByTitle(Long groupId, String title, Integer pageNo) {
        // 페이지 번호 유효성 검사
        if(pageNo == null || pageNo < 1) {
            throw new CommonException(ErrorCode.INVALID_PARAMETER_FORMAT);
        }

        // 제목별 전체 게시글 수 조회
        Integer totalElements = studyGroupBoardMapper.getTotalElementsByTitle(groupId, title);
        if(totalElements == null || totalElements < 1) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_BOARD);
        }

        // 현재 페이지 게시글 조회
        Integer offset = (pageNo - 1) * ELEMENTS_PER_PAGE;
        List<StudyGroupBoardDTO> boards = studyGroupBoardMapper.findStudyGroupBoardsByTitle(groupId, title, ELEMENTS_PER_PAGE, offset);
        if(boards == null || boards.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_BOARD);
        }

        return new PageDTO<>(boards, pageNo, PAGE_SIZE, ELEMENTS_PER_PAGE, totalElements);
    }

    // 게시글 내용으로 조회
    @Override
    public PageDTO<StudyGroupBoardDTO> findStudyGroupBoardsByContent(Long groupId, String content, Integer pageNo) {
        // 페이지 번호 유효성 검사
        if(pageNo == null || pageNo < 1) {
            throw new CommonException(ErrorCode.INVALID_PARAMETER_FORMAT);
        }

        // 내용별 전체 게시글 수 조회
        Integer totalElements = studyGroupBoardMapper.getTotalElementsByContent(groupId, content);
        if(totalElements == null || totalElements < 1) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_BOARD);
        }

        // 현재 페이지 게시글 조회
        Integer offset = (pageNo - 1) * ELEMENTS_PER_PAGE;
        List<StudyGroupBoardDTO> boards = studyGroupBoardMapper.findStudyGroupBoardsByContent(groupId, content, ELEMENTS_PER_PAGE, offset);
        if(boards == null || boards.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_BOARD);
        }

        return new PageDTO<>(boards, pageNo, PAGE_SIZE, ELEMENTS_PER_PAGE, totalElements);
    }

    // 게시글 단건 조회
    @Override
    public StudyGroupBoardDTO findStudyGroupBoardByBoardId(Long boardId) {
        StudyGroupBoardDTO board = studyGroupBoardMapper.findStudyGroupBoardByBoardId(boardId);
        if (board == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_BOARD);
        }
        return board;
    }

}
