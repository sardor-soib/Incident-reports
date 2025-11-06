package com.reports.event_report.service.impl;

import com.reports.event_report.repository.FunctionalityRepository;
import com.reports.event_report.repository.entity.Functionality;
import com.reports.event_report.service.FunctionalityManager;
import com.reports.event_report.service.mapper.FunctionalityMapper;
import com.reports.event_report.web.dto.FunctionalityDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionalityService implements FunctionalityManager {

    private final FunctionalityRepository functionalityRepository;
    private final FunctionalityMapper functionalityMapper;
    Logger log = LoggerFactory.getLogger(FunctionalityService.class);

    @Autowired
    public FunctionalityService(FunctionalityRepository functionalityRepository, FunctionalityMapper functionalityMapper) {
        this.functionalityRepository = functionalityRepository;
        this.functionalityMapper = functionalityMapper;
    }

    @Override
    public boolean isExistsByName(String name) {
        return functionalityRepository.existsByName(name);
    }

    @Override
    public FunctionalityDTO createFunctionality(@NotNull FunctionalityDTO functionalityDTO) {
        if (isExistsByName(functionalityDTO.name())) {
            log.error("Functionality with name: {} already exists", functionalityDTO.name());
            throw new IllegalArgumentException(String.format("Functionality with name %s already exists", functionalityDTO.name()));
        }
        return functionalityMapper.toDTO(functionalityRepository.save(functionalityMapper.toEntity(functionalityDTO)));
    }

    @Override
    public List<FunctionalityDTO> search(@NotNull @NotBlank String name) {
        log.info("Searching functionalities with name containing: {}", name);
        return functionalityMapper.toDTOList(functionalityRepository.findByNameContainingIgnoreCase(name));
    }

    @Override
    public FunctionalityDTO updateFunctionality(@NotNull Long id, @NotNull FunctionalityDTO functionalityDTO) {
        if (functionalityRepository.existsById(id)) {
            throw new ResourceNotFoundException(String.format("Functionality with id: %d not found", id));
        }
        if (!id.equals(functionalityDTO.id())) {
            throw new IllegalArgumentException("ID in path and DTO do not match");
        }

        return functionalityMapper.toDTO(functionalityRepository.save(functionalityMapper.toEntity(functionalityDTO)));
    }

    @Override
    public void deleteFunctionality(@NotNull Long id) {
        if (functionalityRepository.existsById(id)) {
            throw new ResourceNotFoundException(String.format("Functionality with id: %d not found", id));
        }
        functionalityRepository.deleteById(id);
        log.info("Functionality with id: {} deleted", id);
    }
}
