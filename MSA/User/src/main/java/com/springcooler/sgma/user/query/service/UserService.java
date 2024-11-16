package com.springcooler.sgma.user.query.service;

import com.springcooler.sgma.user.command.domain.aggregate.SignupPath;
import com.springcooler.sgma.user.query.dto.UserDTO;

public interface UserService {
    UserDTO getUserByUserId(Long userId);         // 사용자 ID로 조회
    UserDTO getUserByNickname(String nickname);    // 사용자 닉네임으로 조회

    UserDTO getUserByNicknameForDuplicate(String nickname);
    UserDTO getUserByUserIdentifier(String userIdentifier);       //사용자 identifyer로 조회
    UserDTO getUserByUserAuthId(String userAuthId);
    UserDTO findUserByUserNicknameAndSignupPathAndEmail
           (String nickname, SignupPath signupPath, String email);
}