package com.springcooler.sgma.user.command.application.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.springcooler.sgma.user.command.application.dto.RequestUpdateUserDTO;
import com.springcooler.sgma.user.command.application.dto.UserDTO;
import com.springcooler.sgma.user.command.domain.aggregate.AcceptStatus;
import com.springcooler.sgma.user.command.domain.aggregate.ActiveStatus;
import com.springcooler.sgma.user.command.domain.aggregate.SignupPath;
import com.springcooler.sgma.user.command.domain.aggregate.UserEntity;
import com.springcooler.sgma.user.command.domain.aggregate.vo.RequestResistUserVO;
import com.springcooler.sgma.user.command.domain.repository.UserRepository;
import com.springcooler.sgma.user.common.exception.CommonException;
import com.springcooler.sgma.user.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("userCommandServiceImpl")
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AmazonS3Client s3Client;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;  // StringRedisTemplate 사용


    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    /* 설명. security 모듈 추가 후 암호화를 위해 BCryptPasswordEncoder bean 주입 */
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository
            , AmazonS3Client s3Client
            , BCryptPasswordEncoder bCryptPasswordEncoder
            , ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.s3Client = s3Client;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
    }


    // 이름, 가입 구분, 이메일로 사용자 찾기
    public UserDTO findUserByUserNicknameAndSignupPathAndEmail(String nickname, SignupPath signupPath, String email) {
        UserEntity userEntity = userRepository.findByNicknameAndSignupPathAndEmail(nickname, signupPath, email);
        if (userEntity == null) {
            return null;
        }
        return modelMapper.map(userEntity, UserDTO.class);
    }

    // 아이디와 이메일로 사용자 찾기
    public UserDTO findUserByUserAuthIdAndEmail(String userAuthId, String email) {
        UserEntity userEntity = userRepository.findByUserAuthIdAndEmail(userAuthId, email);
        if (userEntity == null) {
            return null;
        }
        return modelMapper.map(userEntity, UserDTO.class);
    }


    /**
     * 사용자 계정을 비활성화하는 메서드.
     */
    @Override
    public UserEntity deactivateUser(Long userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_USER);
        }
        UserEntity userEntity = user.get();
        userEntity.deactivateUser();  // 사용자 상태를 비활성화(INACTIVE)로 변경
        return userRepository.save(userEntity);  // 변경된 상태를 저장
    }

    /**
     * 사용자 계정을 활성화하는 메서드.
     */
    @Override
    public UserEntity activateUser(String userAuthId) {
        UserEntity user = userRepository.findByUserIdentifier("NORMAL_"+userAuthId);
        if (user==null) {
            throw new CommonException(ErrorCode.NOT_FOUND_USER);
        }
        UserEntity userEntity = user;
        userEntity.activateUser();  // 사용자 상태를 활성화(ACTIVE)로 변경
        return userRepository.save(userEntity);  // 변경된 상태를 저장
    }

    //필기.  로그인 전 사용자 비밀번호 재설정
    @Override
    public UserEntity updatePassword(String userAuthId, String newPassword) {
        // 1. 사용자 ID를 통해 사용자를 조회
        UserEntity user = userRepository.findByUserIdentifier("NORMAL_"+userAuthId);

        //필기. 사용자 존재 검증
        if (user==null ) {
            // 사용자가 존재하지 않으면 예외 발생
            throw new CommonException(ErrorCode.NOT_FOUND_USER);
        }

        //필기. 비번 재설정시 이메일 인증여부 확인
        UserEntity newUser=user;
        // Redis에서 이메일 인증 여부 확인 (이메일이 있을 때만)
        if (newUser.getEmail() != null && !newUser.getEmail().isEmpty()) {
            String emailVerificationStatus = stringRedisTemplate.opsForValue().get(newUser.getEmail());
            if (!"True".equals(emailVerificationStatus)) {
                log.error("이메일 인증이 완료되지 않았습니다: {}", newUser.getEmail());
                throw new CommonException(ErrorCode.EMAIL_VERIFICATION_REQUIRED); // 이메일 인증이 필요하다는 커스텀 예외 던지기
            }
        }

        UserEntity userEntity = user;

        // 2. 새로운 비밀번호를 암호화하여 설정
        String encryptedPassword = bCryptPasswordEncoder.encode(newPassword);
        userEntity.setEncryptedPwd(encryptedPassword);

        // 3. 암호화된 비밀번호를 저장하고 사용자 정보를 업데이트
        UserEntity updatedUserEntity = userRepository.save(userEntity);

        //4. 비번 재설정 성공 후 Redis에서 이메일 키 삭제
        if (newUser.getEmail() != null && !newUser.getEmail().isEmpty()) {
            stringRedisTemplate.delete(newUser.getEmail());
        }
        
        // 5. 업데이트된 사용자 엔티티 반환
        return updatedUserEntity;
    }

    //필기. 로그인 후 사용자 비밀번호 재설정
    @Override
    public UserEntity updateLogiendPassword(Long userId, String newPassword) {
        // 1. 사용자 ID를 통해 사용자를 조회
        Optional<UserEntity> user = userRepository.findById(userId);

        //필기. 사용자 존재 검증
        if (user.isEmpty()) {
            // 사용자가 존재하지 않으면 예외 발생
            throw new CommonException(ErrorCode.NOT_FOUND_USER);
        }

        UserEntity userEntity = user.get();

        // 2. 새로운 비밀번호를 암호화하여 설정
        String encryptedPassword = bCryptPasswordEncoder.encode(newPassword);
        userEntity.setEncryptedPwd(encryptedPassword);

        // 3. 암호화된 비밀번호를 저장하고 사용자 정보를 업데이트
        UserEntity updatedUserEntity = userRepository.save(userEntity);

        // 5. 업데이트된 사용자 엔티티 반환
        return updatedUserEntity;
    }




    /**설명.
     *  사용자의 프로필(닉네임, 이미지)을 업데이트하는 메서드.
     *설명.
     * @param userId 프로필을 업데이트할 사용자의 ID
     * @param userUpdateDTO 업데이트할 정보가 담긴 DTO 객체
     * @return 업데이트된 UserEntity 객체
     * @throws CommonException 사용자가 존재하지 않을 경우 발생
     */
    @Override
    public UserEntity updateProfile(Long userId, RequestUpdateUserDTO userUpdateDTO) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_USER);
        }
        UserEntity userEntity = user.get();

        String imageUrl = null;


        // 닉네임 중복 검증(null이 아니고 기존과 같지 않은 경우에)
        if (userUpdateDTO.getNickname() != null ) {

            if (userUpdateDTO.getNickname().equals(userEntity.getNickname()))
            {
                throw new CommonException(ErrorCode.DUPLICATE_NICKNAME);
            }

            Optional<UserEntity> existingUserWithSameNickname = userRepository.findByNickname(userUpdateDTO.getNickname());
            if (existingUserWithSameNickname.isPresent()) {
                throw new CommonException(ErrorCode.DUPLICATE_NICKNAME_EXISTS); // 커스텀 예외 던지기 (DUPLICATE_NICKNAME은 정의된 에러 코드로 가정)
            }
        }


        // 프로필 이미지가 제공된 경우에만 처리
        if (userUpdateDTO.getProfileImage() != null && !userUpdateDTO.getProfileImage().isEmpty()) {
            // 기존 이미지가 있으면 삭제
            if (userEntity.getProfileImage() != null && !userEntity.getProfileImage().isEmpty()) {
                deleteProfileImage(userEntity.getProfileImage());
            }
            // 새로운 이미지를 업로드하고 URL을 얻음
            imageUrl = uploadProfileImage(userUpdateDTO.getProfileImage(), userId);
        }

        // 닉네임이나 이미지가 null일 수 있으므로 기존 값을 유지할지 여부를 확인
        String updatedNickname = userUpdateDTO.getNickname() != null ? userUpdateDTO.getNickname() : userEntity.getNickname();
        String updatedImageUrl = imageUrl != null ? imageUrl : userEntity.getProfileImage();

        // 프로필 업데이트
        userEntity.updateProfile(updatedNickname, updatedImageUrl);
        return userRepository.save(userEntity);
    }

    /**설명.
     *  S3에서 기존 프로필 이미지를 삭제하는 메서드.
     *설명.
     * @param fileUrl 삭제할 파일의 S3 URL
     */
    public void deleteProfileImage(String fileUrl) {
        String splitStr = ".com/";
        String fileName = fileUrl.substring(fileUrl.lastIndexOf(splitStr) + splitStr.length());

        log.info("Attempting to delete file from S3: " + fileName);

        try {
            // S3에서 파일 삭제 요청
            s3Client.deleteObject(new DeleteObjectRequest(bucket, fileName));
            log.info("Successfully deleted image from S3: " + fileName);
        } catch (AmazonClientException e) {
            log.error("Failed to delete image from S3: " + fileName, e);
        }
    }

    /**설명.
     *  MultipartFile을 S3에 업로드하고, 업로드된 파일의 URL을 반환하는 메서드.
     *설명.
     * @param profileImage 업로드할 프로필 이미지 파일
     * @param userId 사용자의 ID로 파일명을 지정
     * @return 업로드된 파일의 S3 URL
     * @throws CommonException 파일 업로드에 실패할 경우 발생
     */
    private String uploadProfileImage(MultipartFile profileImage, Long userId) {
        String originalFileName = profileImage.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();  // 확장자를 소문자로 변환
        String fileName = "user_" + userId + fileExtension;  // 사용자 ID를 기반으로 파일명 생성

        File file = convertMultipartFileToFile(profileImage);

        try {
            // S3에 파일 업로드 및 퍼블릭 읽기 권한 설정
            s3Client.putObject(new PutObjectRequest(bucket, fileName, file)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            return s3Client.getUrl(bucket, fileName).toString();
        } catch (AmazonClientException e) {
            log.error("S3에 이미지 업로드 실패", e);
            throw new CommonException(ErrorCode.FILE_UPLOAD_ERROR);
        } finally {
            if (file.delete()) {
                log.info("임시 파일 삭제 성공");
            } else {
                log.warn("임시 파일 삭제 실패");
            }
        }
    }

    /**설명.
     *  MultipartFile을 File 객체로 변환하는 메서드.
     *설명.
     * @param file 변환할 MultipartFile 객체
     * @return 변환된 File 객체
     * @throws CommonException 파일 변환에 실패할 경우 발생
     */
    private File convertMultipartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting MultipartFile to File", e);
            throw new CommonException(ErrorCode.FILE_CONVERSION_ERROR);
        }
        return convertedFile;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public synchronized UserDTO registUser(RequestResistUserVO newUser) {

        // 일반 회원가입인데 동일한 UserIdentifier가 존재하는지 확인(이메일 중복 검증)
        UserEntity existingUser = userRepository.findByUserIdentifier("NORMAL_" + newUser.getUserAuthId());
        if (existingUser != null) {
            throw new CommonException(ErrorCode.EXIST_USER_ID);
        }


        // Redis에서 이메일 인증 여부 확인 (이메일이 있을 때만)
        if (newUser.getEmail() != null && !newUser.getEmail().isEmpty()) {
            String emailVerificationStatus = stringRedisTemplate.opsForValue().get(newUser.getEmail());
            if (!"True".equals(emailVerificationStatus)) {
                log.error("이메일 인증이 완료되지 않았습니다: {}", newUser.getEmail());
                throw new CommonException(ErrorCode.EMAIL_VERIFICATION_REQUIRED); // 이메일 인증이 필요하다는 커스텀 예외 던지기
            }
        }
        
        //필기. 일반 회원가입시 닉네임 중복 검증 로직
        if(newUser.getNickname()==null){
            throw new CommonException(ErrorCode.INVALID_INPUT_NICKNAME);
        }
        Optional<UserEntity> existingUserWithSameNickname = userRepository.findByNickname(newUser.getNickname());
        if (existingUserWithSameNickname.isPresent()) {
            throw new CommonException(ErrorCode.DUPLICATE_NICKNAME_EXISTS); // 커스텀 예외 던지기 (DUPLICATE_NICKNAME은 정의된 에러 코드로 가정)
        }



        //필기. aws s3에 올라갈 프로필(추후에 변경 예정)
        String defaultProfileImageUrl = "https://example.com/default-profile.png"; // 기본 프로필 이미지 URL
        
        // UserDTO 설정 (유효성 검사 후)
        UserDTO newUserDTO = UserDTO.builder()
                .userAuthId(newUser.getUserAuthId())  // 사용자가 입력한 ID
                .userName(newUser.getUserName())
                .email(newUser.getEmail())
                .signupPath(SignupPath.NORMAL)
                .createdAt(LocalDateTime.now().withNano(0))
                .acceptStatus(AcceptStatus.Y)
                .userStatus(ActiveStatus.ACTIVE)
                .nickname(newUser.getNickname()) //필기. 닉네임도 회원가입시 필요
                .profileImage(defaultProfileImageUrl) //필기. 기본 프로필 이미지 설정
                .userIdentifier("NORMAL_" + newUser.getUserAuthId()) // user_identifier 생성
                .build();

        // DTO -> Entity 변환
        UserEntity userEntity = modelMapper.map(newUserDTO, UserEntity.class);
        log.info("Service 계층에서 DTO -> Entity: {}", userEntity);

        // 비밀번호 암호화
        userEntity.setEncryptedPwd(bCryptPasswordEncoder.encode(newUser.getPassword()));

        // Entity 저장 후 반환된 Entity 가져오기
        UserEntity savedEntity = userRepository.save(userEntity);

        // 회원가입 성공 후 Redis에서 이메일 키 삭제
        if (newUser.getEmail() != null && !newUser.getEmail().isEmpty()) {
            stringRedisTemplate.delete(newUser.getEmail());
        }

        // 저장된 Entity를 DTO로 변환하여 반환
        return modelMapper.map(savedEntity, UserDTO.class);
    }

    @Override
    public UserEntity findByUserIdentifier(String userIdentifier) {
        return userRepository.findByUserIdentifier(userIdentifier);
    }


    /* 설명. 로그인 시 security가 자동으로 호출하는 메소드 */
    @Override
    public UserDetails loadUserByUsername(String userIdentifier) throws UsernameNotFoundException {

        /* 설명. 넘어온 user_auth_id와 signupPath를 사용자가 입력한 id로써 회원을 조회하는 쿼리 메소드 작성 */
        UserEntity loginUser = userRepository.findByUserIdentifier(userIdentifier);
        if (loginUser == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_USER_ID);
        }


        // 소셜 로그인으로 가입된 사용자의 경우 비밀번호가 없을 수 있으므로 기본 비밀번호 설정
        String encryptedPwd = loginUser.getEncryptedPwd();
        if (encryptedPwd == null) {
            // 비밀번호가 없을 경우 임의의 문자열이나 빈 문자열을 설정
            encryptedPwd = "{noop}"; // 또는 필요한 기본값 설정
        }

        /* 설명. 사용자의 권한들을 가져왔다는 가정 */
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ENTERPRISE"));

        return new User(loginUser.getUserAuthId(), encryptedPwd,
                true, true, true, true,
                grantedAuthorities);
    }
}