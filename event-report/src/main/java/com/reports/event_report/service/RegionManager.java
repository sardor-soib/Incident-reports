package com.reports.event_report.service;

import com.reports.event_report.web.dto.RegionDTO;

import java.util.List;
import java.util.Optional;

public interface RegionManager {

    void create(String name);

    List<RegionDTO> search(String name);

    void update(Long id, RegionDTO regionDTO);

    void delete(Long id);
}
