package com.reports.event_report.repository;

import com.reports.event_report.repository.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    List<Region> findByNameContainingIgnoreCase(String name);

}
