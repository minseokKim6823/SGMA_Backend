package com.springcooler.sgma.studygroupcategory.command.domain.service;

import com.springcooler.sgma.studygroupcategory.command.domain.aggregate.RestStatus;
import com.springcooler.sgma.studygroupcategory.command.application.dto.StudyGroupCategoryDTO;

public interface DomainStudyGroupCategoryService {

    boolean isValidDTO(RestStatus restStatus, StudyGroupCategoryDTO studyGroupDTO);

}
