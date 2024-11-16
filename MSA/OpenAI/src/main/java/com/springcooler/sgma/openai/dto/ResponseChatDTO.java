package com.springcooler.sgma.openai.dto;

import com.springcooler.sgma.openai.aggregate.Choice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseChatDTO {
    private List<Choice> choices;
}
