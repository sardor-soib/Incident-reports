package com.reports.event_report.service;

import com.reports.event_report.web.dto.FunctionalityDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FunctionalityManager {

    boolean isExistsByName(String name);

    FunctionalityDTO createFunctionality(FunctionalityDTO functionalityDTO);

    Page<FunctionalityDTO> search(String name, Pageable pageable);

    FunctionalityDTO updateFunctionality(Long id, FunctionalityDTO functionalityDTO);

    void deleteFunctionality(Long id);

}
