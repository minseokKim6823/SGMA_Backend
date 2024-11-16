package com.springcooler.sgma.user.command.application.service;

import com.springcooler.sgma.user.command.application.dto.RequestUpdateUserDTO;
import com.springcooler.sgma.user.command.application.dto.UserDTO;
import com.springcooler.sgma.user.command.domain.aggregate.vo.RequestUpdateUserVO;
import com.springcooler.sgma.user.command.domain.aggregate.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserEntity deactivateUser(Long userId);
    UserEntity activateUser(Long userId);
    UserEntity updateProfile(Long userId, RequestUpdateUserDTO userUpdateDTO);

    UserDTO registUser(UserDTO userDTO);
}