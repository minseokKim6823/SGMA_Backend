package com.springcooler.sgma.studygroupcategory.command.application.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StudyGroupCategoryDTO {
    private Integer categoryId;
    private String categoryName;
}
