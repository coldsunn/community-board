package com.study.communityboard.repository;

import com.study.communityboard.domain.BoardScrap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface BoardScrapRepository extends JpaRepository<BoardScrap, Long> {

    boolean existsByUser_IdAndBoard_Id(Long userId, Long boardId);

    void deleteByUser_IdAndBoard_Id(Long userId, Long boardId);

    List<BoardScrap> findByUser_Id(Long userId);
}
