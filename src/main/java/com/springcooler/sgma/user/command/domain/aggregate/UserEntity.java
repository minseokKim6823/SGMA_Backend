package com.springcooler.sgma.user.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "USER")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name", nullable = false, length = 255)
    private String userName;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "nickname", nullable = false, length = 255)
    private String nickname;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "user_status", nullable = false, length = 255)
    private String userStatus = "ACTIVE";

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "withdrawn_at")
    private Timestamp withdrawnAt;

    @Column(name = "profile_image", columnDefinition = "TEXT")
    private String profileImage;

    @Column(name = "accept_status", nullable = false, length = 255)
    private String acceptStatus = "N";

    @Column(name = "signup_path", length = 255)
    private String signupPath;

    //필기. 사용자 정보 비활성화로 설정
    public void deactivateUser() {
        this.userStatus = "INACTIVE";
        this.withdrawnAt = new Timestamp(System.currentTimeMillis());
    }

    //필기. 사용자 정보 활성화로 설정
    public void activateUser() {
        this.userStatus = "ACTIVE";
    }
    
    //필기. 사용자 정보 변경(닉네임, 사진)
    public void updateProfile(String nickname, String profileImage) {
        if (nickname != null && !nickname.isEmpty()) {
            this.nickname = nickname;
        }
        if (profileImage != null && !profileImage.isEmpty()) {
            this.profileImage = profileImage;
        }
    }
}
