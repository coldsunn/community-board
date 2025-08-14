package com.study.communityboard.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id; // 카테고리 ID

    @Column(nullable = false, unique = true)
    private String name; // 카테고리명

    @Column(nullable = false)
    private String displayName; // 카테고리 메뉴명(학과명)


}
