package com.springcooler.sgma.studygroupcategory.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="STUDY_GROUP_CATEGORY")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudyGroupCategory {

    @Id
    @Column(name="STUDY_GROUP_CATEGORY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(name="STUDY_GROUP_CATEGORY_NAME")
    private String categoryName;

}
