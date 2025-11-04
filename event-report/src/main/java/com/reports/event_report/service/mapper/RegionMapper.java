package com.reports.event_report.service.mapper;

import com.reports.event_report.repository.entity.Region;
import com.reports.event_report.web.dto.RegionDTO;

import java.util.List;

public interface RegionMapper {

    Region toEntity(RegionDTO regionDTO);

    RegionDTO toDTO(Region region);

    List<Region> toEntityList(List<RegionDTO> regionDTOs);

    List<RegionDTO> toDTOList(List<Region> regions);
}
