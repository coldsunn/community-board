package com.study.communityboard.service;

import com.study.communityboard.domain.Board;
import com.study.communityboard.domain.Category;
import com.study.communityboard.domain.Department;
import com.study.communityboard.domain.User;
import com.study.communityboard.repository.*;
import lombok.RequiredArgsConstructor;
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
    private final BoardLikeRepository boardLikeRepository;
    private final BoardScrapRepository boardScrapRepository;
    private final CommentRepository commentRepository;

    // 게시글 생성
    @Transactional
    public Long create(Long userId, Long categoryId, String title, String content) {
        User user = userRepository.findById(userId).get();
        Category category = categoryRepository.findById(categoryId).get();
        Board board = Board.create(user, category, title, content);

        return boardRepository.save(board).getId();
    }

    // 게시글 내역 확인
    public List<Board> listAll(Department department, Long categoryId) {
        return boardRepository.findByDepartmentAndCategory_IdOrderByCreatedAtDesc(department, categoryId);
    }

    // 특정 키워드로 게시글 검색
    public List<Board> search(Department department, Long categoryId, String keyword) {
        return boardRepository.search(department, categoryId, keyword);
    }

    // ID로 게시글 찾기
    public Board getBoard(Long boardId) {
        return boardRepository.findById(boardId).get();
    }

    // 조회수 증가
    @Transactional
    public void increaseView(Long boardId) {
        boardRepository.increaseView(boardId);
    }

    // 제목, 내용 수정
    @Transactional
    public void update(Long boardId, String title, String content) {
        Board board = boardRepository.findById(boardId).get();
        board.updateTitleAndContent(title, content);
        boardRepository.save(board);
    }

    // 게시글 삭제(관련 정보 모두 삭제)
    @Transactional
    public void delete(Long boardId) {
        boardLikeRepository.deleteByBoard_Id(boardId);
        boardScrapRepository.deleteByBoard_Id(boardId);
        commentRepository.deleteByBoard_Id(boardId);
        boardRepository.deleteById(boardId);
    }

    // 유저의 모든 게시글 찾기
    @Transactional(readOnly = true)
    public List<Board> listByUser(Long userId) {
        return boardRepository.findByUser_IdOrderByCreatedAtAsc(userId);
    }
}
