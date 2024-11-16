package com.springcooler.sgma.recruitmentboardlike.command.domain.service;


import com.springcooler.sgma.recruitmentboardlike.command.application.dto.RecruitmentBoardLikeDTO;
import com.springcooler.sgma.recruitmentboardlike.command.domain.aggregate.RestStatus;
import org.springframework.stereotype.Service;

@Service
public class DomainRecruitmentBoardLikeServiceImpl implements DomainRecruitmentBoardLikeService {

    @Override
    public boolean isValidDTO(RestStatus restStatus, RecruitmentBoardLikeDTO recruitmentBoardLikeDTO) {
        if(recruitmentBoardLikeDTO == null) return false;
        switch (restStatus) {
            case POST:
                if(recruitmentBoardLikeDTO.getRecruitmentBoardId() == null ||
                        recruitmentBoardLikeDTO.getUserId() == null) return false;
                break;
        }

        return true;
    }

}
