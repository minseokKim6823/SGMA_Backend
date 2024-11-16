package com.springcooler.sgma.studygroupcategory.command.domain.service;

import com.springcooler.sgma.studygroupcategory.command.application.dto.StudyGroupCategoryDTO;
import com.springcooler.sgma.studygroupcategory.command.domain.aggregate.RestStatus;
import org.springframework.stereotype.Service;

@Service
public class DomainStudyGroupCategoryServiceImpl implements DomainStudyGroupCategoryService {

    @Override
    public boolean isValidDTO(RestStatus restStatus, StudyGroupCategoryDTO studyGroupDTO) {
        if(studyGroupDTO == null) return false;
        switch (restStatus) {
            case POST:
                if(studyGroupDTO.getCategoryName() == null) return false;
                break;
        }

        return true;
    }

}
