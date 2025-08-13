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
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department department;

    @Column(nullable = false)
    private int viewCount;

    @Column(nullable = false)
    private int likeCount;

    @Column(nullable = false)
    private int scrapCount;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // 생성 메서드
    public static Board create(User user, Category category, String title, String content) {
        Board board = Board.builder()
                .user(user)
                .category(category)
                .title(title)
                .content(content)
                .department(user.getDepartment())
                .viewCount(0)
                .likeCount(0)
                .scrapCount(0)
                .build();
        return board;
    }

    // 조회수, 좋아요, 스크랩, 수정 관련 메서드

    public void increaseLike() {
        this.likeCount += 1;
    }

    public void decreaseLike() {
        if (this.likeCount > 0) this.likeCount -= 1;
    }

    public void increaseScrap() {
        this.scrapCount += 1;
    }

    public void decreaseScrap() {
        if (this.scrapCount > 0) this.scrapCount -= 1;
    }

    public void updateTitleAndContent(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
