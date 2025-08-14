package com.study.communityboard.domain;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id; // 댓글 ID

    @Lob
    @Column(nullable = false)
    private String content; // 내용

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // User와 다대일 단방향 연관관계

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board; // Board와 다대일 단방향 연관관계

    //생성 메서드
    public static Comment create(Board board, User user, String content) {
        return Comment.builder()
                .board(board)
                .user(user)
                .content(content)
                .build();
    }
}
