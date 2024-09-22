package com.springcooler.sgma.user.query.controller;

import com.springcooler.sgma.user.command.domain.aggregate.SignupPath;
import com.springcooler.sgma.user.common.ResponseDTO;
import com.springcooler.sgma.user.common.exception.CommonException;
import com.springcooler.sgma.user.common.exception.ErrorCode;
import com.springcooler.sgma.user.query.dto.*;
import com.springcooler.sgma.user.query.service.RecruitmentCommentService;
import com.springcooler.sgma.user.query.service.UserService;
import org.hibernate.engine.jdbc.cursor.internal.RefCursorSupportInitiator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

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
    public ResponseDTO<UserDTO> getUserByNickName(@PathVariable("nickname") String nickname) {
        UserDTO userDTO = userService.getUserByNickname(nickname);
        return ResponseDTO.ok(userDTO);
    }

    // 사용자 이메일로 사용자 조회
    @GetMapping("/identifier")
    public ResponseDTO<UserDTO> getUserByUserIdentifier(@RequestBody RequestUserIdentifierDTO requestUserIdentifierDTO) {
        UserDTO userDTO = userService.getUserByUserIdentifier(requestUserIdentifierDTO.getUserIdentifier());
        return ResponseDTO.ok(userDTO);
    }

    @GetMapping("/auth-id")
    public ResponseDTO<UserDTO> getUserByUserNameAndSignupPathAndEmail(@RequestBody RequestUserAuthIdDTO request) {
        //필기. 닉네임, 가입 구분, 이메일이 일치하는 사용자가 있는지 확인
        UserDTO userDTO = userService.findUserByUserNicknameAndSignupPathAndEmail(request.getNickname(), SignupPath.NORMAL, request.getEmail());
        return ResponseDTO.ok(userDTO);
    }



    // 회원별 댓글 조회 API
    @GetMapping("/comments/{userId}")
    public UserCommentsAndRepliesDTO getCommentsAndRepliesByUserId(@PathVariable("userId") Long userId) {
        return recruitmentCommentService.getCommentsAndRepliesByUserId(userId);
    }
}


