package com.reports.event_report.service;

import com.reports.event_report.web.dto.RegionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RegionManager {

    RegionDTO createRegion(RegionDTO regionDTO);

    Page<RegionDTO> search(String name, Pageable pageable);

    RegionDTO updateRegion(Long id, RegionDTO regionDTO);

    void deleteRegion(Long id);
}
