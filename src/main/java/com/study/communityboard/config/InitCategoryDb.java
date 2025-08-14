package com.study.communityboard.config;

import com.study.communityboard.domain.Category;
import com.study.communityboard.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitCategoryDb implements ApplicationRunner {

    private final CategoryRepository categoryRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (categoryRepository.count() > 0) {
            return;
        }

        // 애플리케이션 시작과 동시에 게시판 분류 저장 -> 쿼리 5번 나가기 때문에 구조 개선 필요
        List<Category> categories = Arrays.asList(
                Category.builder().name("NOTICE").displayName("공지사항").build(),
                Category.builder().name("COUNCIL_NOTICE").displayName("학생회 공지사항").build(),
                Category.builder().name("FREE_BOARD").displayName("자유 게시판").build(),
                Category.builder().name("CAREER_BOARD").displayName("진로 게시판").build(),
                Category.builder().name("CLASS_BOARD").displayName("수업 게시판").build()
        );

        categoryRepository.saveAll(categories);
    }

}
