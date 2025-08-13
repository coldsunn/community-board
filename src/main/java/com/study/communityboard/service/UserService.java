package com.study.communityboard.service;

import com.study.communityboard.domain.Department;
import com.study.communityboard.domain.Role;
import com.study.communityboard.domain.User;
import com.study.communityboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long register(String studentId, String nickname, String password, Department department, Role role) {
        if (userRepository.existsByStudentId(studentId)) {
            throw new IllegalArgumentException("이미 존재하는 학번입니다.");
        }

        if (userRepository.existsByNickname(nickname)) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }

        User user = User.create(studentId, nickname, password, department, role);
        return userRepository.save(user).getId();
    }

    @Transactional(readOnly = true)
    public User login(String studentId, String password) {
        return userRepository.findByStudentIdAndPassword(studentId, password)
                .orElse(null);
    }

    @Transactional(readOnly = true)
    public User getProfile(Long userId) {
        return userRepository.findById(userId)
                .orElse(null);
    }

    public void withdraw(Long userId) {
        userRepository.deleteById(userId);
    }
}
