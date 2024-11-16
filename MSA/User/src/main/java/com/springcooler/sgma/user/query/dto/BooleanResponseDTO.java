package com.springcooler.sgma.user.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BooleanResponseDTO {
    @JsonProperty("exist") // Lombok이 생성한 getter 이름을 맞추도록 필드명 수정
    private boolean isExist;
}