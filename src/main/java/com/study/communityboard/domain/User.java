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
    private Long id;

    @Column(columnDefinition = "CHAR(10)", nullable = false, unique = true)
    private String studentId;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department department;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

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
