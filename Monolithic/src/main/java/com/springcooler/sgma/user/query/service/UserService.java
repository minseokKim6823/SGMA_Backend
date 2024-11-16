package com.springcooler.sgma.user.query.service;

import com.springcooler.sgma.user.query.dto.UserDTO;
import com.springcooler.sgma.user.query.repository.UserMapper;

public interface UserService {
    UserDTO getUserByUserId(Long userId);         // 사용자 ID로 조회
    UserDTO getUserByNickname(String nickname);    // 사용자 닉네임으로 조회

}