package com.springcooler.sgma.studygroupboard.query.service;

import com.springcooler.sgma.studygroupboard.query.dto.PageDTO;
import com.springcooler.sgma.studygroupboard.query.dto.StudyGroupBoardDTO;

public interface StudyGroupBoardService {

    // 게시글 그룹별 조회
    PageDTO<StudyGroupBoardDTO> findStudyGroupBoardsByGroupId(Long groupId, Integer pageNo);

    // 게시글 그룹원별 조회
    PageDTO<StudyGroupBoardDTO> findStudyGroupBoardsByMemberId(Long memberId, Integer pageNo);

    // 게시글 제목으로 조회
    PageDTO<StudyGroupBoardDTO> findStudyGroupBoardsByTitle(Long groupId, String title, Integer pageNo);

    // 게시글 내용으로 조회
    PageDTO<StudyGroupBoardDTO> findStudyGroupBoardsByContent(Long groupId, String content, Integer pageNo);

    // 게시글 단건 조회
    StudyGroupBoardDTO findStudyGroupBoardByBoardId(Long boardId);

}
