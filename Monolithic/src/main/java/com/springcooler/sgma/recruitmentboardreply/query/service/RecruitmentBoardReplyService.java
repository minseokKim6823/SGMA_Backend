package com.springcooler.sgma.recruitmentboardreply.query.service;

import com.springcooler.sgma.recruitmentboardreply.query.dto.RecruitmentBoardReplyDTO;
import com.springcooler.sgma.recruitmentboardreply.query.repository.RecruitmentBoardReplyMapper;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.springcooler.sgma.studygroupapplicant.common.Template.getSqlSession;

@Service
public class RecruitmentBoardReplyService {

    public List<RecruitmentBoardReplyDTO> getAllRecruitmentBoardReply(){
        SqlSession sqlSession =getSqlSession();
        RecruitmentBoardReplyMapper mapper =sqlSession.getMapper(RecruitmentBoardReplyMapper.class);
        List<RecruitmentBoardReplyDTO> recruitmentBoardReplyDTO =mapper.recruitmentBoardReplyDTO();
        return recruitmentBoardReplyDTO;
    }
}
