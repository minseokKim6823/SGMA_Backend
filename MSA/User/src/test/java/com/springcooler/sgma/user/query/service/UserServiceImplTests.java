//package com.springcooler.sgma.user.query.service;
//
//import com.springcooler.sgma.user.query.dto.UserDTO;
//import com.springcooler.sgma.user.query.repository.UserMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import java.time.LocalDateTime;
//
//@Slf4j
//@SpringBootTest
//@Transactional
//class UserServiceImplTests {
//
//    @Autowired
//    private UserService userService; // 실제 UserServiceImpl 사용
//
//    @Autowired
//    private UserMapper userMapper; // 실제 UserMapper 사용
//
//    @Test
//    @DisplayName("회원 id를 통한 단건 조회 테스트")
//    void getUserById_success() {
//        log.info("회원 ID로 사용자 조회 테스트 시작");
//
//        // 이미 데이터베이스에 존재하는 user_id 1에 대한 테스트
//        UserDTO foundUser = userService.getUserByUserId(1L);
//
//        log.info("조회된 사용자 정보: {}", foundUser);
//
//        assertThat(foundUser).isNotNull();
//        assertThat(foundUser.getUserId()).isEqualTo(1L);
//        assertThat(foundUser.getUserName()).isEqualTo("조찬국");
//        assertThat(foundUser.getEmail()).isEqualTo("alice@example.com");
//
//        // expected LocalDateTime
//        LocalDateTime expectedDateTime = LocalDateTime.of(2023, 8, 1, 10, 30);
//        assertThat(foundUser.getCreatedAt()).isEqualTo(expectedDateTime);
//
//        log.info("회원 ID로 사용자 조회 테스트 성공");
//    }
//
//    @Test
//    @DisplayName("회원 닉네임을 통한 단건 조회 테스트")
//    void getUserByNickname_success() {
//        log.info("회원 닉네임으로 사용자 조회 테스트 시작");
//
//        // 이미 데이터베이스에 존재하는 nickname "조찬국"에 대한 테스트
//        UserDTO foundUser = userService.getUserByNickname("alice01");
//
//        log.info("조회된 사용자 정보: {}", foundUser);
//
//        assertThat(foundUser).isNotNull();
//        assertThat(foundUser.getNickname()).isEqualTo("alice01");
//        assertThat(foundUser.getEmail()).isEqualTo("alice@example.com");
//
//        log.info("회원 닉네임으로 사용자 조회 테스트 성공");
//    }
//}
