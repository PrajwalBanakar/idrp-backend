package com.idrp.backend.repository;

import com.idrp.backend.entity.ProgramApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramApplicationRepository extends JpaRepository<ProgramApplication, Long> {

    boolean existsByEmailAndProgramId(String email, Long programId);

    boolean existsByEmailAndProgramIdAndIdNot(String email, Long programId, Long id);
}