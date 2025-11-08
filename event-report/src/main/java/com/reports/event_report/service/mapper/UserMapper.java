package com.reports.event_report.service.mapper;

import com.reports.event_report.repository.entity.User;
import com.reports.event_report.web.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "group.id", source = "groupId")
    User toEntity(UserDTO userDTO);

    @Mapping(target = "groupId", source = "group.id")
    UserDTO toDTO(User user);

    List<User> toEntityList(List<UserDTO> userDTOs);

    List<UserDTO> toDTOList(List<User> users);

    default Page<UserDTO> toDTOPage(Page<User> page) {
        List<UserDTO> dtoList = toDTOList(page.getContent());
        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }
}
