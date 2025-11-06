package com.reports.event_report.service;

import com.reports.event_report.web.dto.RegionDTO;

import java.util.List;

public interface RegionManager {

    RegionDTO createRegion(String name);

    List<RegionDTO> search(String name);

    RegionDTO updateRegion(Long id, RegionDTO regionDTO);

    void deleteRegion(Long id);
}
