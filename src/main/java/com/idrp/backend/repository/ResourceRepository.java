package com.idrp.backend.repository;

import com.idrp.backend.entity.Resource;
import com.idrp.backend.entity.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    boolean existsBySlug(String slug);

    boolean existsBySlugAndIdNot(String slug, Long id);

    Optional<Resource> findBySlug(String slug);

}