package com.idrp.backend.repository;

import com.idrp.backend.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {

    boolean existsByProgramName(String programName);

    boolean existsByProgramNameAndIdNot(String programName, Long id);
}