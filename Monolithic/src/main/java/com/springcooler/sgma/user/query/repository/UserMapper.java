package com.springcooler.sgma.user.query.repository;

import com.springcooler.sgma.user.query.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    UserDTO findByUserId(@Param("userId") Long userId);          // 사용자 ID로 조회
    UserDTO findByNickname(@Param("nickname") String nickname);  // 사용자 닉네임으로 조회
}
