//package com.springcooler.sgma.user.command.application.service;
//
//import com.amazonaws.AmazonClientException;
//import com.amazonaws.services.s3.AmazonS3Client;
//import com.amazonaws.services.s3.model.PutObjectRequest;
//import com.springcooler.sgma.user.command.application.dto.RequestUpdateUserDTO;
//import com.springcooler.sgma.user.command.domain.aggregate.ActiveStatus;
//import com.springcooler.sgma.user.command.domain.aggregate.UserEntity;
//import com.springcooler.sgma.user.command.domain.repository.UserRepository;
//import com.springcooler.sgma.user.common.exception.CommonException;
//import com.springcooler.sgma.user.common.exception.ErrorCode;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.temporal.ChronoUnit;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class UserServiceImplTests {
//
//    @Autowired
//    private UserServiceImpl userServiceImpl;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @MockBean
//    private AmazonS3Client s3Client;
//
//    @Test
//    @DisplayName("사용자 비활성화 성공 테스트")
//    void deactivateUser_Success() {
//        // Given
//        Long userId = 1L;  // 실제 DB에 존재하는 사용자 ID로 변경 필요
//        UserEntity userEntity = userRepository.findById(userId)
//                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
//
//        // When
//        UserEntity result = userServiceImpl.deactivateUser(userId);
//
//        // Then
//        assertNotNull(result);
//        assertEquals(ActiveStatus.INACTIVE, result.getUserStatus());
//        assertNotNull(result.getWithdrawnAt());
//    }
//
//    @Test
//    @DisplayName("존재하지 않는 사용자 비활성화 실패 테스트")
//    void deactivateUser_UserNotFound() {
//        // Given
//        Long userId = 999L;  // 존재하지 않는 사용자 ID로 테스트
//
//        // When & Then
//        CommonException exception = assertThrows(CommonException.class, () -> userServiceImpl.deactivateUser(userId));
//        assertEquals(ErrorCode.NOT_FOUND_USER, exception.getErrorCode());
//    }
//
////    @Test
////    @DisplayName("사용자 활성화 성공 테스트")
////    void activateUser_Success() {
////        // Given
////        Long userId = 1L;  // 실제 DB에 존재하는 사용자 ID로 변경 필요
////        UserEntity userEntity = userRepository.findById(userId)
////                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
////
////        // When
////        UserEntity result = userServiceImpl.activateUser(userId);
////
////        // Then
////        assertNotNull(result);
////        assertEquals(ActiveStatus.ACTIVE, result.getUserStatus());
////    }
//
////    @Test
////    @DisplayName("존재하지 않는 사용자 활성화 실패 테스트")
////    void activateUser_UserNotFound() {
////        // Given
////        Long userId = 999L;  // 존재하지 않는 사용자 ID로 테스트
////
////        // When & Then
////        CommonException exception = assertThrows(CommonException.class, () -> userServiceImpl.activateUser(userId));
////        assertEquals(ErrorCode.NOT_FOUND_USER, exception.getErrorCode());
////    }
//
//    @Test
//    @DisplayName("사용자 비활성화 시간 정확성 검증 테스트")
//    void deactivateUser_TimeExactMatchVerification() {
//        // Given
//        Long userId = 1L;  // 실제 DB에 존재하는 사용자 ID로 변경 필요
//        UserEntity userEntity = userRepository.findById(userId)
//                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
//
//        // When
//        UserEntity result = userServiceImpl.deactivateUser(userId);
//
//        // Then
//        assertNotNull(result);
//        assertEquals(ActiveStatus.INACTIVE, result.getUserStatus());
//
//        // 현재 시간을 기준으로 withdrawnAt이 정확히 같은지 확인 (초 단위까지)
//        LocalDateTime now = LocalDateTime.now().withNano(0);
//        LocalDateTime withdrawnAt = result.getWithdrawnAt();
//        assertNotNull(withdrawnAt);
//        assertTrue(ChronoUnit.SECONDS.between(withdrawnAt, now) < 1,
//                "withdrawnAt 시간이 현재 시간과 1초 이내여야 합니다.");
//    }
//
//    @Test
//    @DisplayName("사용자 프로필 업데이트 성공 테스트")
//    void updateProfile_Success() throws IOException {
//        // Given
//        Long userId = 1L;
//        UserEntity userEntity = userRepository.findById(userId)
//                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
//
//        MockMultipartFile mockMultipartFile = new MockMultipartFile("profileImage", "test.jpg",
//                "image/jpeg", "test image content".getBytes());
//
//        RequestUpdateUserDTO userUpdateDTO = new RequestUpdateUserDTO();
//        userUpdateDTO.setNickname("새로운 닉네임");
//        userUpdateDTO.setProfileImage(mockMultipartFile);
//
//        // S3 파일 업로드 Mocking
//        Mockito.when(s3Client.putObject(Mockito.any(PutObjectRequest.class)))
//                .thenReturn(null);
//        Mockito.when(s3Client.getUrl(Mockito.anyString(), Mockito.anyString()))
//                .thenReturn(new java.net.URL("https://s3.amazonaws.com/bucket/test.jpg"));
//
//        // When
//        UserEntity result = userServiceImpl.updateProfile(userId, userUpdateDTO);
//
//        // Then
//        assertNotNull(result);
//        assertEquals("새로운 닉네임", result.getNickname());
//        assertEquals("https://s3.amazonaws.com/bucket/test.jpg", result.getProfileImage());
//    }
//
//    @Test
//    @DisplayName("존재하지 않는 사용자 프로필 업데이트 실패 테스트")
//    void updateProfile_UserNotFound() {
//        // Given
//        Long userId = 999L;  // 존재하지 않는 사용자 ID로 테스트
//        MockMultipartFile mockMultipartFile = new MockMultipartFile("profileImage", "test.jpg",
//                "image/jpeg", "test image content".getBytes());
//
//        RequestUpdateUserDTO userUpdateDTO = new RequestUpdateUserDTO();
//        userUpdateDTO.setNickname("새로운 닉네임");
//        userUpdateDTO.setProfileImage(mockMultipartFile);
//
//        // When & Then
//        CommonException exception = assertThrows(CommonException.class, () -> userServiceImpl.updateProfile(userId, userUpdateDTO));
//        assertEquals(ErrorCode.NOT_FOUND_USER, exception.getErrorCode());
//    }
//
//    @Test
//    @DisplayName("프로필 이미지 업로드 실패 테스트")
//    void updateProfile_FileUploadFailure() throws IOException {
//        // Given
//        Long userId = 1L;
//        UserEntity userEntity = userRepository.findById(userId)
//                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
//
//        MockMultipartFile mockMultipartFile = new MockMultipartFile("profileImage", "test.jpg",
//                "image/jpeg", "test image content".getBytes());
//
//        RequestUpdateUserDTO userUpdateDTO = new RequestUpdateUserDTO();
//        userUpdateDTO.setNickname("새로운 닉네임");
//        userUpdateDTO.setProfileImage(mockMultipartFile);
//
//        // S3 파일 업로드 실패 Mocking
//        Mockito.doThrow(new AmazonClientException("S3 upload failed"))
//                .when(s3Client).putObject(Mockito.any(PutObjectRequest.class));
//
//        // When & Then
//        CommonException exception = assertThrows(CommonException.class, () -> userServiceImpl.updateProfile(userId, userUpdateDTO));
//        assertEquals(ErrorCode.FILE_UPLOAD_ERROR, exception.getErrorCode());
//    }
//}
