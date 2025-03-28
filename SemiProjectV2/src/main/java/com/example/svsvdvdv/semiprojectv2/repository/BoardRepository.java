package com.example.svsvdvdv.semiprojectv2.repository;

import com.example.svsvdvdv.semiprojectv2.domain.Board;
import com.example.svsvdvdv.semiprojectv2.domain.BoardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Entity;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

//    @Query(value = "select bno, title, userid, regdate, thumbs, views from boards order by bno desc limit :stnum, :pageSize",
//            nativeQuery = true)
//    List<BoardDTO> findBoards(int stnum, int pageSize);

    // 위 내용을 아래 한줄로 바꿈(jpa에 맞게)

    Page<BoardDTO> findBy(Pageable pageable);

    Page<BoardDTO> findByTitleContains(Pageable pageable, String findkey);
    Page<BoardDTO> findByUseridContains(Pageable pageable, String findkey);
    Page<BoardDTO> findByContentsContains(Pageable pageable, String findkey);

    Page<BoardDTO> findByTitleContainsOrContentsContains(Pageable pageable, String fkey1, String fkey2);

    Board findByBno(Long bno);

    @Modifying
    @Query("update Board set views = views + 1 where bno = :bno")
    int updateViews(@Param("bno") Long bno);

//    @Query("select b from Board b left join fetch b.replies where b.bno = :bno")
//    Board findByBnoWithReply(@Param("bno") Long bno);

//    @EntityGraph(attributePaths = {"reply"})
//    Board findByBno(Long bno);
}
