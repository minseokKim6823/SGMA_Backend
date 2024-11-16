package com.springcooler.sgma.studygroupcategory.command.application.controller;

import com.springcooler.sgma.studygroupcategory.command.application.dto.StudyGroupCategoryDTO;
import com.springcooler.sgma.studygroupcategory.command.application.service.AppStudyGroupCategoryService;
import com.springcooler.sgma.studygroupcategory.command.domain.aggregate.vo.RequestStudyGroupCategoryVO;
import com.springcooler.sgma.studygroupcategory.command.domain.aggregate.vo.ResponseStudyGroupCategoryVO;
import com.springcooler.sgma.studygroupcategory.common.ResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController("commandStudyGroupCategoryController")
@RequestMapping("/api/study-group/categories")
public class StudyGroupCategoryController {

    private final ModelMapper modelMapper;
    private final AppStudyGroupCategoryService studyGroupCategoryService;

    public StudyGroupCategoryController(ModelMapper modelMapper,
                                        AppStudyGroupCategoryService studyGroupCategoryService) {
        this.modelMapper = modelMapper;
        this.studyGroupCategoryService = studyGroupCategoryService;
    }

    // 스터디그룹 카테고리 생성
    @PostMapping
    public ResponseDTO<?> registerStudyGroupCategory(@RequestBody RequestStudyGroupCategoryVO newCategory) {
        StudyGroupCategoryDTO category = modelMapper.map(newCategory, StudyGroupCategoryDTO.class);
        category = studyGroupCategoryService.registStudyGroupCategory(category);
        ResponseStudyGroupCategoryVO res = modelMapper.map(category, ResponseStudyGroupCategoryVO.class);
        return ResponseDTO.ok(res);
    }

    // 스터디그룹 카테고리 삭제
    @DeleteMapping("/{categoryId}")
    public ResponseDTO<?> deleteStudyGroupCategory(@PathVariable("categoryId") Integer categoryId) {
        studyGroupCategoryService.deleteStudyGroupCategory(categoryId);
        return ResponseDTO.ok(null);
    }

}
