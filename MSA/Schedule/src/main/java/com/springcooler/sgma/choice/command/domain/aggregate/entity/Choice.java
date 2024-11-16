package com.springcooler.sgma.choice.command.domain.aggregate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name="CHOICE")
public class Choice {

    @EmbeddedId
    private ChoicePK choicePK;

    @Column(name="content")
    private String content;

}
