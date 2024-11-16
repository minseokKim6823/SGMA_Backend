package com.springcooler.sgma.user.query.service;

import com.springcooler.sgma.user.common.exception.CommonException;
import com.springcooler.sgma.user.common.exception.ErrorCode;
import com.springcooler.sgma.user.query.dto.UserDTO;
import com.springcooler.sgma.user.query.repository.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("userQueryServiceImpl")
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO getUserByUserId(Long userId) {
        UserDTO user = userMapper.findByUserId(userId);
        log.info("user: {}",user);
        if (user == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_USER);
        }
        return user;
    }

    @Override
    public UserDTO getUserByNickname(String nickname) {
        UserDTO user = userMapper.findByNickname(nickname);
        if (user == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_USER);
        }
        return user;
    }
}
