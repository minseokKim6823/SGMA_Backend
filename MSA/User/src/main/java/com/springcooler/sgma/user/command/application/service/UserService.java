package com.springcooler.sgma.user.command.application.service;

import com.springcooler.sgma.user.command.application.dto.RequestUpdateUserDTO;
import com.springcooler.sgma.user.command.application.dto.UserDTO;
import com.springcooler.sgma.user.command.domain.aggregate.SignupPath;
import com.springcooler.sgma.user.command.domain.aggregate.vo.RequestResistUserVO;
import com.springcooler.sgma.user.command.domain.aggregate.vo.RequestUpdateUserVO;
import com.springcooler.sgma.user.command.domain.aggregate.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    UserEntity deactivateUser(Long userId);
    UserEntity activateUser(String userId);
    UserEntity updateProfile(Long userId, RequestUpdateUserDTO userUpdateDTO);
    UserEntity updatePassword(String userAuthId,String password);

    UserEntity updateLogiendPassword(Long userId,String password);
    UserDTO registUser(RequestResistUserVO newUser);
    UserEntity findByUserIdentifier(String userIdentifier);

    // 이름과 이메일로 사용자 찾기
    UserDTO findUserByUserNicknameAndSignupPathAndEmail(String nickname, SignupPath signupPath, String email);
    // 아이디와 이메일로 사용자 찾기
    UserDTO findUserByUserAuthIdAndEmail(String userAuthId, String email);
}