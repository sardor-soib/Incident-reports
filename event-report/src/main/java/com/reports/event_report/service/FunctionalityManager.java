package com.reports.event_report.service;

import com.reports.event_report.web.dto.FunctionalityDTO;

import java.util.List;

public interface FunctionalityManager {

    boolean isExistsByName(String name);

    void create(String name);

    List<FunctionalityDTO> search(String name);

    void update(Long id, FunctionalityDTO functionalityDTO);

    void delete(Long id);

}
