package com.reports.event_report.service.impl;

import com.reports.event_report.repository.RegionRepository;
import com.reports.event_report.repository.entity.Region;
import com.reports.event_report.service.RegionManager;
import com.reports.event_report.service.mapper.RegionMapper;
import com.reports.event_report.web.dto.RegionDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RegionService implements RegionManager {

    private final Logger log = LoggerFactory.getLogger(RegionService.class);

    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    @Autowired
    public RegionService(RegionRepository regionRepository, RegionMapper regionMapper) {
        this.regionRepository = regionRepository;
        this.regionMapper = regionMapper;
    }

    @Override
    public RegionDTO createRegion(@NotNull RegionDTO regionDTO) {
        log.info("Creating region with name: {}", regionDTO.name());
        return regionMapper.toDTO(regionRepository.save(new Region(null, regionDTO.name())));
    }

    @Override
    public Page<RegionDTO> search(@NotNull @NotBlank String name, @NotNull Pageable pageable) {
        log.info("Searching regions with name containing: {}", name);
        return regionMapper.toDTOPage(regionRepository.findByNameContainingIgnoreCase(name, pageable));
    }

    @Override
    public RegionDTO updateRegion(@NotNull Long id, @NotNull RegionDTO regionDTO) {
        if (!regionRepository.existsById(id)) {
            log.error("Region with id {} does not exist", id);
            throw new IllegalArgumentException(String.format("Region with id %d does not exist", id));
        }
        if (!id.equals(regionDTO.id())) {
            log.error("Region id in path {} does not match id in body {}", id, regionDTO.id());
            throw new IllegalArgumentException(String.format("Region id in path %d does not match id in body %d", id, regionDTO.id()));
        }
        return regionMapper.toDTO(regionRepository.save(regionMapper.toEntity(regionDTO)));
    }

    @Override
    public void deleteRegion(@NotNull Long id) {
        if (!regionRepository.existsById(id)) {
            log.error("Region with id {} does not exist", id);
            throw new IllegalArgumentException(String.format("Region with id %d does not exist", id));
        }
        regionRepository.deleteById(id);
        log.info("Region with id {} deleted successfully", id);
    }
}
