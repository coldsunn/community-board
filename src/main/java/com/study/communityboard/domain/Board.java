package com.study.communityboard.domain;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Board extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id; // 게시판 ID

    @Column(nullable = false)
    private String title; // 제목

    @Lob
    @Column(nullable = false)
    private String content; // 내용

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department department; // 학과

    @Column(nullable = false)
    private int viewCount; // 조회수

    @Column(nullable = false)
    private int likeCount; // 좋아요 수

    @Column(nullable = false)
    private int scrapCount; // 스크랩 수

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // User와 다대일 단방향 연관관계

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category; // Category와 다대일 단방향 연관관계

    // 생성 메서드
    public static Board create(User user, Category category, String title, String content) {
        Board board = Board.builder()
                .user(user)
                .category(category)
                .title(title)
                .content(content)
                .department(user.getDepartment()) // 유저의 학과와 동일
                .viewCount(0) // 초기값 0
                .likeCount(0) // 초기값 0
                .scrapCount(0) // 초기값 0
                .build();
        return board;
    }

    // 조회수, 좋아요, 스크랩, 수정 관련 메서드

    public void increaseLike() {
        this.likeCount++;
    }

    public void decreaseLike() {
        if (this.likeCount > 0) this.likeCount--;
    }

    public void increaseScrap() {
        this.scrapCount++;
    }

    public void decreaseScrap() {
        if (this.scrapCount > 0) this.scrapCount--;
    }

    public void updateTitleAndContent(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
