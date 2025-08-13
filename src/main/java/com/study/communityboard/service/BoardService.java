package com.study.communityboard.service;

import com.study.communityboard.domain.Board;
import com.study.communityboard.domain.Category;
import com.study.communityboard.domain.Department;
import com.study.communityboard.domain.User;
import com.study.communityboard.repository.BoardRepository;
import com.study.communityboard.repository.CategoryRepository;
import com.study.communityboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Long create(Long userId, Long categoryId, String title, String content) {
        User user = userRepository.findById(userId).get();
        Category category = categoryRepository.findById(categoryId).get();
        Board board = Board.create(user, category, title, content);

        return boardRepository.save(board).getId();
    }

    public List<Board> listAll(Department department, Long categoryId) {
        return boardRepository.findByDepartmentAndCategory_IdOrderByCreatedAtDesc(department, categoryId);
    }

    public List<Board> search(Department department, Long categoryId, String keyword) {
        return boardRepository.search(department, categoryId, keyword);
    }

    public Board getBoard(Long boardId) {
        return boardRepository.findById(boardId).get();
    }

    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).get();
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Transactional
    public void increaseView(Long boardId) {
        boardRepository.increaseView(boardId);
    }

    @Transactional
    public void update(Long boardId, String title, String content) {
        Board board = boardRepository.findById(boardId).get();
        board.updateTitleAndContent(title, content);
        boardRepository.save(board);
    }

    @Transactional
    public void delete(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    @Transactional(readOnly = true)
    public List<Board> listByUser(Long userId) {
        return boardRepository.findByUser_IdOrderByCreatedAtAsc(userId);
    }
}
