package com.study.communityboard.repository;

import com.study.communityboard.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBoard_IdOrderByCreatedAtAsc(Long boardId);

    List<Comment> findByUser_IdOrderByCreatedAtAsc(Long userId);

}
