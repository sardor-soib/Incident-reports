package com.reports.event_report.repository;

import com.reports.event_report.repository.entity.Functionality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunctionalityRepository extends JpaRepository<Functionality, Long> {

    boolean existsByName(String name);

    List<Functionality> findByNameContainingIgnoreCase(String name);

}
