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
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardLikeService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;

    public boolean toggle(Long boardId, Long userId) {
        Board board = boardRepository.findById(boardId).get();
        User user = userRepository.findById(userId).get();

        boolean exists = boardLikeRepository.existsByUser_IdAndBoard_Id(userId, boardId);
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

    @Transactional(readOnly = true)
    public boolean check(Long boardId, Long userId) {
        return boardLikeRepository.existsByUser_IdAndBoard_Id(userId, boardId);
    }

    @Transactional(readOnly = true)
    public List<Board> listLikedBoards(Long userId) {
        return boardLikeRepository.findByUser_Id(userId)
                .stream()
                .map(BoardLike::getBoard)
                .collect(toList());
    }
}
