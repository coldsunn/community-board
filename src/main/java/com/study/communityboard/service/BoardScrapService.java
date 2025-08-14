package com.study.communityboard.service;

import com.study.communityboard.domain.Board;
import com.study.communityboard.domain.BoardScrap;
import com.study.communityboard.domain.User;
import com.study.communityboard.repository.BoardRepository;
import com.study.communityboard.repository.BoardScrapRepository;
import com.study.communityboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.*;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardScrapService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final BoardScrapRepository boardScrapRepository;

    // 스크랩 버튼 기능 구현
    public boolean toggle(Long boardId, Long userId) {
        Board board = boardRepository.findById(boardId).get();
        User user = userRepository.findById(userId).get();

        // 유저 당 스크랩은 한 번만 가능, 토글 형식
        boolean exists = boardScrapRepository.existsByUser_IdAndBoard_Id(userId, boardId);
        if (exists) {
            boardScrapRepository.deleteByUser_IdAndBoard_Id(userId, boardId);
            board.decreaseScrap();
            boardRepository.save(board);
            return false;
        } else {
            boardScrapRepository.save(BoardScrap.builder().board(board).user(user).build());
            board.increaseScrap();
            boardRepository.save(board);
            return true;
        }
    }

    // 유저의 스크랩 여부 확인
    @Transactional(readOnly = true)
    public boolean check(Long boardId, Long userId) {
        return boardScrapRepository.existsByUser_IdAndBoard_Id(userId, boardId);
    }

    // 유저의 스크랩 내역
    @Transactional(readOnly = true)
    public List<Board> listScrappedBoards(Long userId) {
        return boardScrapRepository.findByUser_Id(userId)
                .stream()
                .map(BoardScrap::getBoard)
                .collect(toList());
    }
}
