package com.study.communityboard.repository;

import com.study.communityboard.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByStudentId(String studentId);

    Optional<User> findByNickname(String nickname);

    Optional<User> findByStudentIdAndPassword(String studentId, String password);

    boolean existsByStudentId(String studentId);

    boolean existsByNickname(String nickname);

}
