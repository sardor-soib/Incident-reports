package com.reports.event_report.service.mapper;

import com.reports.event_report.repository.entity.Region;
import com.reports.event_report.web.dto.RegionDTO;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RegionMapper {

    Region toEntity(RegionDTO regionDTO);

    RegionDTO toDTO(Region region);

    List<Region> toEntityList(List<RegionDTO> regionDTOs);

    List<RegionDTO> toDTOList(List<Region> regions);

    default Page<RegionDTO> toDTOPage(Page<Region> page) {
        List<RegionDTO> dtoList = toDTOList(page.getContent());
        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }
}
