package com.springcooler.sgma.user.query.service;

import com.springcooler.sgma.user.command.domain.aggregate.SignupPath;
import com.springcooler.sgma.user.command.domain.aggregate.UserEntity;
import com.springcooler.sgma.user.common.exception.CommonException;
import com.springcooler.sgma.user.common.exception.ErrorCode;
import com.springcooler.sgma.user.query.dto.UserDTO;
import com.springcooler.sgma.user.query.repository.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service("userQueryServiceImpl")
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;  // StringRedisTemplate 사용

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

    @Override
    public UserDTO getUserByUserIdentifier(String userIdentifier) {
        UserDTO user = userMapper.findByUserIdentifier(userIdentifier);
        log.info("user: {}",user);
        if (user == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_USER);
        }
        return user;
    }
    @Override
    public UserDTO getUserByUserAuthId(String userAuthId) {

        UserDTO user = userMapper.findByUserIdentifier("NORMAL_"+userAuthId);
        log.info("user: {}",user);
        return user;
    }

    @Override
    public UserDTO getUserByNicknameForDuplicate(String nickname) {
        UserDTO user = userMapper.findByNickname(nickname);
        return user;
    }

    @Override
    public UserDTO findUserByUserNicknameAndSignupPathAndEmail(String nickname, SignupPath signupPath, String email) {
        UserDTO user = userMapper.findByNicknameAndSignupPathAndEmail(nickname, signupPath, email);
        log.info("user: {}", user);
        if (user == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_USER);
        }

        //필기. 아이디 재설정시 이메일 인증여부 확인
        // Redis에서 이메일 인증 여부 확인 (이메일이 있을 때만)
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            String emailVerificationStatus = stringRedisTemplate.opsForValue().get(user.getEmail());
            if (!"True".equals(emailVerificationStatus)) {
                log.error("이메일 인증이 완료되지 않았습니다: {}", user.getEmail());
                throw new CommonException(ErrorCode.EMAIL_VERIFICATION_REQUIRED); // 이메일 인증이 필요하다는 커스텀 예외 던지기
            }
        }

        // 아이디 재설정 성공 후 Redis에서 이메일 키 삭제
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            stringRedisTemplate.delete(user.getEmail());
        }

        return user;
    }
}
