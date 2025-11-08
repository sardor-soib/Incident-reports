package com.reports.event_report.repository;

import com.reports.event_report.repository.entity.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    boolean existsByName(String name);

    Page<Group> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
