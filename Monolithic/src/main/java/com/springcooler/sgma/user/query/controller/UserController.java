package com.springcooler.sgma.user.query.controller;

import com.springcooler.sgma.user.common.ResponseDTO;
import com.springcooler.sgma.user.query.dto.RecruitmentBoardCommentDTO;
import com.springcooler.sgma.user.query.dto.UserCommentsAndRepliesDTO;
import com.springcooler.sgma.user.query.dto.UserDTO;
import com.springcooler.sgma.user.query.service.RecruitmentCommentService;
import com.springcooler.sgma.user.query.service.UserService;
import org.hibernate.engine.jdbc.cursor.internal.RefCursorSupportInitiator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userQueryController")
@RequestMapping("/api/users")
public class UserController {

    private final Environment env;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final RecruitmentCommentService recruitmentCommentService;

    @Autowired
    public UserController(Environment env, ModelMapper modelMapper, UserService userService, RecruitmentCommentService recruitmentCommentService) {
        this.env = env;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.recruitmentCommentService = recruitmentCommentService;
    }

    // 사용자 ID로 조회
    @GetMapping("/user-id/{userId}")
    public ResponseDTO<UserDTO> getUserById(@PathVariable("userId") Long userId) {
        UserDTO userDTO = userService.getUserByUserId(userId);
        return ResponseDTO.ok(userDTO);
    }

    // 닉네임으로 사용자 조회
    @GetMapping("/nickname/{nickname}")
    public ResponseDTO<UserDTO> getUserByName(@PathVariable("nickname") String nickname) {
        UserDTO userDTO = userService.getUserByNickname(nickname);
        return ResponseDTO.ok(userDTO);
    }

    // 회원별 댓글 조회 API
    @GetMapping("/comments/{userId}")
    public UserCommentsAndRepliesDTO getCommentsAndRepliesByUserId(@PathVariable("userId") Long userId) {
        return recruitmentCommentService.getCommentsAndRepliesByUserId(userId);
    }
}


