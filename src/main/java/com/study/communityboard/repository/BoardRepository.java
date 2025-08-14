package com.study.communityboard.repository;

import com.study.communityboard.domain.Board;
import com.study.communityboard.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByUser_IdOrderByCreatedAtAsc(Long userId);

    List<Board> findByDepartmentAndCategory_IdOrderByCreatedAtDesc(Department department, Long categoryId);

    void deleteByUser_Id(Long userId);

    @Query("""
           select b from Board b
           join b.user u
           where b.department = :department
             and b.category.id = :categoryId
             and (lower(b.title) like lower(concat('%', :q, '%')) or lower(u.nickname) like lower(concat('%', :q, '%')))
           order by b.createdAt desc
           """)
    List<Board> search(
            @Param("department") Department department,
            @Param("categoryId") Long categoryId,
            @Param("q") String keyword);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Board b set b.viewCount = b.viewCount + 1 where b.id = :boardId")
    void increaseView(@Param("boardId") Long boardId);
}
