package com.reports.event_report.repository;

import com.reports.event_report.repository.entity.Functionality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionalityRepository extends JpaRepository<Functionality, Long> {

    boolean existsByName(String name);

    Page<Functionality> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
