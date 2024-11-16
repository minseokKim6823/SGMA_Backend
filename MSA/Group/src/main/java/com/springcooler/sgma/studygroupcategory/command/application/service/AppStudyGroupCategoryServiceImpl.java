package com.springcooler.sgma.studygroupcategory.command.application.service;

import com.springcooler.sgma.studygroupcategory.command.application.dto.StudyGroupCategoryDTO;
import com.springcooler.sgma.studygroupcategory.command.domain.aggregate.RestStatus;
import com.springcooler.sgma.studygroupcategory.command.domain.aggregate.StudyGroupCategory;
import com.springcooler.sgma.studygroupcategory.command.domain.repository.StudyGroupCategoryRepository;
import com.springcooler.sgma.studygroupcategory.command.domain.service.DomainStudyGroupCategoryService;
import com.springcooler.sgma.studygroupcategory.common.exception.CommonException;
import com.springcooler.sgma.studygroupcategory.common.exception.ErrorCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppStudyGroupCategoryServiceImpl implements AppStudyGroupCategoryService {

    private final ModelMapper modelMapper;
    private final DomainStudyGroupCategoryService domainStudyGroupCategoryService;
    private final StudyGroupCategoryRepository studyGroupCategoryRepository;

    @Autowired
    public AppStudyGroupCategoryServiceImpl(ModelMapper modelMapper,
                                            DomainStudyGroupCategoryService domainStudyGroupCategoryService,
                                            StudyGroupCategoryRepository studyGroupCategoryRepository) {
        this.modelMapper = modelMapper;
        this.domainStudyGroupCategoryService = domainStudyGroupCategoryService;
        this.studyGroupCategoryRepository = studyGroupCategoryRepository;
    }

    // 스터디그룹 카테고리 생성
    @Transactional
    @Override
    public StudyGroupCategoryDTO registStudyGroupCategory(StudyGroupCategoryDTO newCategory) {
        // DTO 유효성 검사
        if(!domainStudyGroupCategoryService.isValidDTO(RestStatus.POST, newCategory))
            throw new CommonException(ErrorCode.INVALID_REQUEST_BODY);

        StudyGroupCategory category = modelMapper.map(newCategory, StudyGroupCategory.class);
        studyGroupCategoryRepository.save(category);

        return modelMapper.map(category, StudyGroupCategoryDTO.class);
    }

    // 스터디그룹 카테고리 삭제
    @Transactional
    @Override
    public void deleteStudyGroupCategory(Integer categoryId) {
        // 기존 엔티티 조회
        StudyGroupCategory deleteCategory =
                studyGroupCategoryRepository.findById(categoryId).orElseThrow(
                        () -> new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_CATEGORY));

        studyGroupCategoryRepository.delete(deleteCategory);
    }

}
