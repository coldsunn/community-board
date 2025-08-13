package com.study.communityboard.domain;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "board_id"}))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class BoardScrap {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_scrap_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

}
