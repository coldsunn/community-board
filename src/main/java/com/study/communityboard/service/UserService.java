package com.study.communityboard.service;

import com.study.communityboard.domain.*;
import com.study.communityboard.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final BoardScrapRepository boardScrapRepository;
    private final CommentRepository commentRepository;

    // 유저 회원가입
    public Long register(String studentId, String nickname, String password, Department department, Role role) {
        if (userRepository.existsByStudentId(studentId)) {
            throw new IllegalArgumentException("이미 존재하는 학번입니다.");
        }

        if (userRepository.existsByNickname(nickname)) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }

        User user = User.create(studentId, nickname, password, department, role);
        return userRepository.save(user).getId();
    }

    // 로그인
    @Transactional(readOnly = true)
    public User login(String studentId, String password) {
        return userRepository.findByStudentIdAndPassword(studentId, password)
                .orElse(null);
    }

    // 유저 정보 찾기
    @Transactional(readOnly = true)
    public User getProfile(Long userId) {
        return userRepository.findById(userId)
                .orElse(null);
    }

    // 회원 탈퇴
    public void withdraw(Long userId) {
        List<BoardLike> likedBoards = boardLikeRepository.findByUser_Id(userId);
        for (BoardLike likedBoard : likedBoards) {
            Board board = likedBoard.getBoard();
            board.decreaseLike();
        }
        boardLikeRepository.deleteByUser_Id(userId);

        List<BoardScrap> scrappedBoards = boardScrapRepository.findByUser_Id(userId);
        for (BoardScrap scrappedBoard : scrappedBoards) {
            Board board = scrappedBoard.getBoard();
            board.decreaseScrap();
        }
        boardScrapRepository.deleteByUser_Id(userId);

        commentRepository.deleteByUser_Id(userId);
        boardRepository.deleteByUser_Id(userId);

        userRepository.deleteById(userId);
    }
}
