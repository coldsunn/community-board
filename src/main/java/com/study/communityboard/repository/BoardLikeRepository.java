package com.study.communityboard.repository;

import com.study.communityboard.domain.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {

    List<BoardLike> findByUser_Id(Long userId);

    boolean existsByUser_IdAndBoard_Id(Long userId, Long boardId);

    void deleteByUser_Id(Long userId);

    void deleteByBoard_Id(Long boardId);

    void deleteByUser_IdAndBoard_Id(Long userId, Long boardId);
}
