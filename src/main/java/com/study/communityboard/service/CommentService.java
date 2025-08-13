package com.study.communityboard.service;

import com.study.communityboard.domain.Board;
import com.study.communityboard.domain.Comment;
import com.study.communityboard.domain.User;
import com.study.communityboard.repository.BoardRepository;
import com.study.communityboard.repository.CommentRepository;
import com.study.communityboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public Long add(Long boardId, Long userId, String content) {
        Board board = boardRepository.findById(boardId).get();
        User user = userRepository.findById(userId).get();
        Comment comment = Comment.create(board, user, content);

        return commentRepository.save(comment).getId();
    }

    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Transactional(readOnly = true)
    public List<Comment> listByBoard(Long boardId) {
        return commentRepository.findByBoard_IdOrderByCreatedAtAsc(boardId);
    }

    @Transactional(readOnly = true)
    public List<Comment> listByUser(Long userId) {
        return commentRepository.findByUser_IdOrderByCreatedAtAsc(userId);
    }
}
