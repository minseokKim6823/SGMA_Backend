package com.springcooler.sgma.user.command.application.controller;

import com.springcooler.sgma.user.command.application.dto.*;
import com.springcooler.sgma.user.command.application.service.EmailVerificationService;
import com.springcooler.sgma.user.command.application.service.OAuth2LoginService;
import com.springcooler.sgma.user.command.domain.aggregate.SignupPath;
import com.springcooler.sgma.user.command.domain.aggregate.vo.*;
import com.springcooler.sgma.user.command.domain.aggregate.vo.kakao.KakaoAuthorizationCode;
import com.springcooler.sgma.user.command.domain.aggregate.vo.login.AuthTokens;
import com.springcooler.sgma.user.command.domain.aggregate.vo.login.ResponseOAuthLoginVO;
import com.springcooler.sgma.user.command.domain.aggregate.vo.naver.NaverAuthorizationCode;
import com.springcooler.sgma.user.common.ResponseDTO;
import com.springcooler.sgma.user.common.exception.CommonException;
import com.springcooler.sgma.user.common.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.springcooler.sgma.user.command.application.service.UserService;
import com.springcooler.sgma.user.command.domain.aggregate.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@RestController("userCommandController")
@RequestMapping("/api/users")
public class UserController {
    private final Environment env;
    private final UserService userService;
    private final ModelMapper modelMapper;

    private final OAuth2LoginService oAuth2LoginService;
    @Autowired
    public UserController(Environment env, UserService userService,ModelMapper modelMapper,
                          OAuth2LoginService oAuth2LoginService) {
        this.env = env;
        this.userService = userService;
        this.modelMapper=modelMapper;
        this.oAuth2LoginService = oAuth2LoginService;
    }

    @GetMapping("/health")
    public String status() {
        return "I'm Working in User Service on port "
                + env.getProperty("local.server.port");
    }

    //필기. 사용자 활성화 여부 변경
    @PatchMapping("/{userId}/deactivate")
    public ResponseDTO<?> deactivateUser(@PathVariable("userId") Long userId) {
        UserEntity userEntity = userService.deactivateUser(userId);
        return ResponseDTO.ok(userEntity);
    }
    //필기. 사용자 활성화 여부 변경
    @PostMapping("/activate")
    public ResponseDTO<?> activateUser(@RequestParam("userAuthId") String userAuthId ) {
        UserEntity userEntity = userService.activateUser(userAuthId);
        return ResponseDTO.ok(userEntity);
    }

    //필기. 사용자 정보 변경(닉네임,사진)
    @PatchMapping("/{userId}/profile")
    public ResponseDTO<?> updateProfile(@PathVariable("userId") Long userId,
                                        @RequestParam("nickname") String nickname,
                                        @RequestParam(value = "profile_image", required = false) MultipartFile profileImage) {

        // DTO 객체 생성 및 값 설정
        RequestUpdateUserDTO userUpdateDTO = new RequestUpdateUserDTO();
        userUpdateDTO.setNickname(nickname);
        userUpdateDTO.setProfileImage(profileImage);

        // 서비스 호출 및 결과 처리
        UserEntity userEntity = userService.updateProfile(userId, userUpdateDTO);
        ResponseUserVO userUpdateRequestVO = modelMapper.map(userEntity, ResponseUserVO.class);
        return ResponseDTO.ok(userUpdateRequestVO);
    }


    //필기. 로그인전 사용자 비밀번호 재설정
    @PostMapping("/re-password")
    public ResponseDTO<?> updatePassword(@RequestBody RequestUpdatePasswordUserVO requestUpdatePasswordUserVO) {

        // 서비스 호출 및 결과 처리
        UserEntity userEntity = userService.updatePassword( requestUpdatePasswordUserVO.getUserAuthId(), requestUpdatePasswordUserVO.getPassword());
        ResponseUserVO userUpdateRequestVO = modelMapper.map(userEntity, ResponseUserVO.class);
        return ResponseDTO.ok(userUpdateRequestVO);
    }

    //필기. 로그인한 사용자 비밀번호 재설정
    @PatchMapping("/{userId}/password")
    public ResponseDTO<?> updateLoginedPassword(@PathVariable("userId") Long userId,
                                        @RequestBody RequestUpdateLoggedInPasswordVO requestUpdatePasswordUserVO) {
        // 서비스 호출 및 결과 처리
        UserEntity userEntity = userService.updateLogiendPassword(userId, requestUpdatePasswordUserVO.getPassword());
        ResponseUserVO userUpdateRequestVO = modelMapper.map(userEntity, ResponseUserVO.class);
        return ResponseDTO.ok(userUpdateRequestVO);
    }




    /*설명. 일반 회원 가입 기능*/
    @PostMapping("/signup/normal")
    public ResponseDTO<?> registNormalUser(@RequestBody RequestResistUserVO newUser) {

        // UserService 호출
        UserDTO savedUserDTO = userService.registUser(newUser); // 저장된 DTO 반환
        
        //응답 VO로 변환후 반환
        ResponseUserVO responseUser=modelMapper.map(savedUserDTO,ResponseUserVO.class);
        return ResponseDTO.ok(responseUser);
    }


    // 설명. 이메일 인증 서비스
    @Autowired
    private EmailVerificationService emailVerificationService;

    // 설명. 이메일 전송 API (회원가입전, 아이디 찾기시 실행)
    @PostMapping("/verification-email/signup")
    public ResponseDTO<?> sendVerificationEmail(@RequestBody @Validated EmailVerificationVO request) {
        emailVerificationService.sendVerificationEmail(request.getEmail());

        ResponseEmailMessageVO responseEmailMessageVO = new ResponseEmailMessageVO();
        responseEmailMessageVO.setMessage("인증 코드가 이메일로 전송되었습니다.");
        return ResponseDTO.ok(responseEmailMessageVO);
    }

    // 설명. 이메일 전송 API(아이디 찾기시 실행)
    @PostMapping("/verification-email/auth-id")
    public ResponseDTO<?> sendVerificationEmailForUserId(@RequestBody @Validated EmailVerificationUserIdRequestDTO request) {
        // 닉네임, 가입 구분, 이메일이 일치하는 사용자가 있는지 확인
        UserDTO user = userService.findUserByUserNicknameAndSignupPathAndEmail(request.getNickname(), SignupPath.NORMAL, request.getEmail());

        if (user == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_USER);
        }

        // 유효성 검사후 가능하면 이메일 전송
        emailVerificationService.sendVerificationEmail(request.getEmail());

        ResponseEmailMessageVO responseEmailMessageVO = new ResponseEmailMessageVO();
        responseEmailMessageVO.setMessage("아이디 찾기를 위한 인증 코드가 이메일로 전송되었습니다.");
        return ResponseDTO.ok(responseEmailMessageVO);
    }

    // 설명. 이메일 전송 API (비밀번호 찾기시 실행)
    @PostMapping("/verification-email/user-password")
    public ResponseDTO<?> sendVerificationEmailForUserPassword(@RequestBody @Validated EmailVerificationUserPasswordRequestDTO request) {
        // NORMAL_{userAuthId}와 이메일이 일치하는 사용자가 있는지 확인
        UserDTO user = userService.findUserByUserAuthIdAndEmail(request.getUserAuthId(), request.getEmail());

        if (user == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_USER);
        }

        // 유효성 검사후 가능하면 이메일 전송
        emailVerificationService.sendVerificationEmail(request.getEmail());

        ResponseEmailMessageVO responseEmailMessageVO = new ResponseEmailMessageVO();
        responseEmailMessageVO.setMessage("비밀번호 찾기를 위한 인증 코드가 이메일로 전송되었습니다.");
        return ResponseDTO.ok(responseEmailMessageVO);
    }


    //설명. 이메일 인증번호 검증 API (회원가입전 실행)
    @PostMapping("/verification-email/confirmation")
    public ResponseDTO<?> verifyEmail(@RequestBody @Validated EmailVerificationVO request) {
        boolean isVerified = emailVerificationService.verifyCode(request.getEmail(), request.getCode());

        ResponseEmailMessageVO responseEmailMessageVO =new ResponseEmailMessageVO();
        responseEmailMessageVO.setMessage("이메일 인증이 완료되었습니다.");
        if (isVerified) {
            return ResponseDTO.ok(responseEmailMessageVO);
        } else {
            return ResponseDTO.fail(new CommonException(ErrorCode.INVALID_VERIFICATION_CODE));
        }
    }

    //설명. 아이디 찾기시 이메일 인증번호 검증 API
    @PostMapping("/nickname/verification-email")
    public ResponseDTO<?> verifyUserIdEmail(@RequestBody @Validated UserIdEmailVerificationVO request) {
        try {
            // 이메일과 인증 코드 검증
            UserDTO userDTO = emailVerificationService.verifyUserNicknameCode(request.getNickname(), request.getEmail(), request.getCode());
            return ResponseDTO.ok(userDTO);
        } catch (CommonException e) {
            // 검증 실패 시 예외 처리
            return ResponseDTO.fail(e);
        }
    }

    //설명. 카카오 로그인, 네이버 로그인
    // 설명. 카카오 로그인
    @PostMapping("/oauth2/kakao")
    public ResponseDTO<ResponseOAuthLoginVO> loginWithKakao(@RequestBody KakaoAuthorizationCode code) {
        AuthTokens tokens = oAuth2LoginService.loginWithKakao(code.getCode());

        // 응답 본문에 필요한 정보 포함
        ResponseOAuthLoginVO loginResponseVO = new ResponseOAuthLoginVO(
                tokens.getAccessToken(),
                new Date(tokens.getAccessTokenExpiry()),
                tokens.getRefreshToken(),
                new Date(tokens.getRefreshTokenExpiry()),
                tokens.getUserIdentifier(),
                tokens.isProfileIncomplete() // 프로필 정보 불완전 여부
        );

        return ResponseDTO.ok(loginResponseVO);
    }

    @PostMapping("/oauth2/naver")
    public ResponseDTO<ResponseOAuthLoginVO> loginWithNaver(@RequestBody NaverAuthorizationCode code) {
        AuthTokens tokens = oAuth2LoginService.loginWithNaver(code);

        // 응답 본문에 필요한 정보 포함
        ResponseOAuthLoginVO loginResponseVO = new ResponseOAuthLoginVO(
                tokens.getAccessToken(),
                new Date(tokens.getAccessTokenExpiry()),
                tokens.getRefreshToken(),
                new Date(tokens.getRefreshTokenExpiry()),
                tokens.getUserIdentifier(),
                tokens.isProfileIncomplete() // 프로필 정보 불완전 여부
        );

        return ResponseDTO.ok(loginResponseVO);
    }

}