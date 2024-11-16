package com.springcooler.sgma.recruitmentboard.query.repository;

import com.springcooler.sgma.recruitmentboard.query.dto.RecruitmentBoardDTO;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface RecruitmentBoardMapper {
    

    RecruitmentBoardDTO findRecruitmentBoardByBoardId(Long recruitmentBoardId);

    List<RecruitmentBoardDTO> findRecruitmentBoardsByTitle(String title);

    List<RecruitmentBoardDTO> findAllRecruitmentBoards();

    List<RecruitmentBoardDTO> findRecruitmentBoardsByGroupId(Long groupId);

    List<RecruitmentBoardDTO> findRecruitmentBoardsByCategory(Integer categoryId);

    List<RecruitmentBoardDTO> findTop3MostLikedPostsWithin7Days();
}
