package com.reports.event_report.repository;

import com.reports.event_report.repository.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    boolean existsByName(String name);

    List<Group> findByNameContainingIgnoreCase(String name);

}
