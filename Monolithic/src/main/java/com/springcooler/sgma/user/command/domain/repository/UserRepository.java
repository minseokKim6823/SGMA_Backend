package com.springcooler.sgma.user.command.domain.repository;

import com.springcooler.sgma.user.command.domain.aggregate.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
//    UserEntity findByEmail(String email);
    @Query("SELECT COALESCE(MAX(u.userId), 0) FROM UserEntity u")
    Long findMaxUserId();

    UserEntity findByUserIdentifier(String userIdentifier);

    Optional<UserEntity> findByNickname(String nickname);
}
