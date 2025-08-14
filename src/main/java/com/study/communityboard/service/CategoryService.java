package com.study.communityboard.service;

import com.study.communityboard.domain.Category;
import com.study.communityboard.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // ID로 카테고리 찾기
    public Category find(Long categoryId) {
        return categoryRepository.findById(categoryId).get();
    }

    // 모든 카테고리 찾기
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
