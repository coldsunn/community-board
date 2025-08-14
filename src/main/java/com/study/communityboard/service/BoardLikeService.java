package com.study.communityboard.service;

import com.study.communityboard.domain.Board;
import com.study.communityboard.domain.BoardLike;
import com.study.communityboard.domain.User;
import com.study.communityboard.repository.BoardLikeRepository;
import com.study.communityboard.repository.BoardRepository;
import com.study.communityboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.*;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardLikeService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;

    // 좋아요 버튼 기능 구현
    public boolean toggle(Long boardId, Long userId) {
        Board board = boardRepository.findById(boardId).get();
        User user = userRepository.findById(userId).get();

        boolean exists = boardLikeRepository.existsByUser_IdAndBoard_Id(userId, boardId);

        // 유저 당 좋아요는 한 번만 가능, 토글 형식
        if (exists) {
            boardLikeRepository.deleteByUser_IdAndBoard_Id(userId, boardId);
            board.decreaseLike();
            boardRepository.save(board);
            return false;
        } else {
            boardLikeRepository.save(BoardLike.builder().board(board).user(user).build());
            board.increaseLike();
            boardRepository.save(board);
            return true;
        }
    }

    // 유저의 좋아요 여부 확인
    @Transactional(readOnly = true)
    public boolean check(Long boardId, Long userId) {
        return boardLikeRepository.existsByUser_IdAndBoard_Id(userId, boardId);
    }

    // 유저의 좋아요 내역
    @Transactional(readOnly = true)
    public List<Board> listLikedBoards(Long userId) {
        return boardLikeRepository.findByUser_Id(userId)
                .stream()
                .map(BoardLike::getBoard)
                .collect(toList());
    }
}
