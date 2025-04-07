package com.example.svsvdvdv.semiprojectv2.repository;

import com.example.svsvdvdv.semiprojectv2.domain.Pds;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PdsRepository extends JpaRepository<Pds, Long> {
    Pds findByPno (int pno);
}
