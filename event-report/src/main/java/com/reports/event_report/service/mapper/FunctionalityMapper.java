package com.reports.event_report.service.mapper;

import com.reports.event_report.repository.entity.Functionality;
import com.reports.event_report.web.dto.FunctionalityDTO;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FunctionalityMapper {

    Functionality toEntity(FunctionalityDTO functionalityDTO);

    FunctionalityDTO toDTO(Functionality functionality);

    List<Functionality> toEntityList(List<FunctionalityDTO> functionalityDTOs);

    List<FunctionalityDTO> toDTOList(List<Functionality> functionalities);

    default Page<FunctionalityDTO> toDTOPage(Page<Functionality> page) {
        List<FunctionalityDTO> dtoList = toDTOList(page.getContent());
        return new PageImpl<FunctionalityDTO>(dtoList, page.getPageable(), page.getTotalElements());
    }
}
