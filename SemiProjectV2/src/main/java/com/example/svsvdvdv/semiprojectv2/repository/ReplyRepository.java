package com.example.svsvdvdv.semiprojectv2.repository;

import com.example.svsvdvdv.semiprojectv2.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    //List<Reply> findByPnoOrderByRef (Long pno);
}
