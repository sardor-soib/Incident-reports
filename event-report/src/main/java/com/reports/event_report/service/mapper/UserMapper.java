package com.reports.event_report.service.mapper;

import com.reports.event_report.repository.entity.User;
import com.reports.event_report.web.dto.UserDTO;

import java.util.List;

public interface UserMapper {

    User toEntity(UserDTO userDTO);

    UserDTO toDTO(User user);

    List<User> toEntityList(List<UserDTO> userDTOs);

    List<UserDTO> toDTOList(List<User> users);
}
