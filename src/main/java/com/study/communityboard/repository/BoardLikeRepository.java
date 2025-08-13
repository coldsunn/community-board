package com.study.communityboard.repository;

import com.study.communityboard.domain.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {

    Optional<BoardLike> findByUser_IdAndBoard_Id(Long userId, Long boardId);

    boolean existsByUser_IdAndBoard_Id(Long userId, Long boardId);

    void deleteByUser_IdAndBoard_Id(Long userId, Long boardId);

    List<BoardLike> findByUser_Id(Long userId);
}
