package com.study.communityboard.repository;

import com.study.communityboard.domain.BoardScrap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardScrapRepository extends JpaRepository<BoardScrap, Long> {

    List<BoardScrap> findByUser_Id(Long userId);

    boolean existsByUser_IdAndBoard_Id(Long userId, Long boardId);

    void deleteByUser_Id(Long userId);

    void deleteByBoard_Id(Long boardId);

    void deleteByUser_IdAndBoard_Id(Long userId, Long boardId);
}
