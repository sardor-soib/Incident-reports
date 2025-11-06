package com.reports.event_report.service;

import com.reports.event_report.web.dto.FunctionalityDTO;

import java.util.List;

public interface FunctionalityManager {

    boolean isExistsByName(String name);

    FunctionalityDTO createFunctionality(FunctionalityDTO functionalityDTO);

    List<FunctionalityDTO> search(String name);

    FunctionalityDTO updateFunctionality(Long id, FunctionalityDTO functionalityDTO);

    void deleteFunctionality(Long id);

}
