package com.springcooler.sgma.user.command.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RequestUpdateUserDTO {
    private String nickname;
    @JsonProperty("profile_image")
    private MultipartFile profileImage;  // 클라이언트가 보낸 파일
}
