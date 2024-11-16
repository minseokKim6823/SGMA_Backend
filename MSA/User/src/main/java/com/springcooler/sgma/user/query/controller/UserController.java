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

    @GetMapping("/identifier")
    public ResponseDTO<UserDTO> getUserByUserIdentifier(@RequestParam("user_identifier") String userIdentifier)  {
        UserDTO userDTO = userService.getUserByUserIdentifier(userIdentifier);
        return ResponseDTO.ok(userDTO);
    }

    //필기. 사용자 아이디 찾기
    @GetMapping("/auth-id")
    public ResponseDTO<com.springcooler.sgma.user.query.dto.UserDTO> getUserByUserNameAndSignupPathAndEmail(
            @RequestParam String nickname,
            @RequestParam String email) {
        //필기. 닉네임, 가입 구분, 이메일이 일치하는 사용자가 있는지 확인
        UserDTO userDTO = userService.findUserByUserNicknameAndSignupPathAndEmail(nickname, SignupPath.NORMAL, email);
        return ResponseDTO.ok(userDTO);
    }

    // 회원별 댓글 조회 API
    @GetMapping("/comments/{userId}")
    public ResponseDTO<UserCommentsAndRepliesDTO> getCommentsAndRepliesByUserId(@PathVariable("userId") Long userId) {
        return ResponseDTO.ok(recruitmentCommentService.getCommentsAndRepliesByUserId(userId));
    }


    // 닉네임 중복 검증 api
    @PostMapping("/nickname/validate")
    public ResponseDTO<BooleanResponseDTO> validateNickname(@RequestBody RequestNicknameDTO requestNicknameDTO) {
        UserDTO userDTO = userService. getUserByNicknameForDuplicate(requestNicknameDTO.getNickname());
        boolean isDuplicate=true;
        if (userDTO==null){
            isDuplicate=false;
        }
        BooleanResponseDTO booleanResponseDTO=new BooleanResponseDTO(isDuplicate);
        return ResponseDTO.ok(booleanResponseDTO);
    }

    // 아이디 중복 검증 api
    @PostMapping("/user-id/validate")
    public ResponseDTO<BooleanResponseDTO> getUserByUserIdentifier(@RequestBody RequestUserAuthIdentifierDTO requestUserIdentifierDTO) {
        UserDTO userDTO = userService.getUserByUserAuthId(requestUserIdentifierDTO.getUserAuthId());
        boolean isDuplicate=true;
        if (userDTO==null){
            isDuplicate=false;
        }
        BooleanResponseDTO booleanResponseDTO=new BooleanResponseDTO(isDuplicate);
        return ResponseDTO.ok(booleanResponseDTO);
    }


}
