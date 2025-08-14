package com.study.communityboard.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id; // 유저 ID

    @Column(columnDefinition = "CHAR(10)", nullable = false, unique = true)
    private String studentId; // 학번

    @Column(nullable = false, unique = true)
    private String nickname; // 닉네임

    @Column(nullable = false)
    private String password; // 비밀번호

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department department; // 학과(경영학과, 국어국문학과...)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // 역할(일반, 학생회)

    // 생성 메서드
    public static User create(String studentId, String nickname, String encodedPassword, Department department, Role role) {
        return User.builder()
                .studentId(studentId)
                .nickname(nickname)
                .password(encodedPassword)
                .department(department)
                .role(role)
                .build();
    }
}
