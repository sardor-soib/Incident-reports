package com.reports.event_report.service.mapper;

import com.reports.event_report.repository.entity.Functionality;
import com.reports.event_report.web.dto.FunctionalityDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface FunctionalityMapper {

    Functionality toEntity(FunctionalityDTO functionalityDTO);

    FunctionalityDTO toDTO(Functionality functionality);

    List<Functionality> toEntityList(List<FunctionalityDTO> functionalityDTOs);

    List<FunctionalityDTO> toDTOList(List<Functionality> functionalities);
}
