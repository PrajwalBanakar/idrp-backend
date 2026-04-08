package com.idrp.backend.repository;

import com.idrp.backend.entity.StartupApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StartupApplicationRepository extends JpaRepository<StartupApplication, Long> {

    boolean existsByStartupNameAndEmail(String startupName, String email);

    boolean existsByStartupNameAndEmailAndIdNot(String startupName, String email, Long id);
}