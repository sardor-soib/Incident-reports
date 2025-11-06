package com.reports.event_report.service;

import com.reports.event_report.repository.entity.User;
import com.reports.event_report.web.dto.UserDTO;

import java.util.List;

public interface UserManager {

    UserDTO createUser(UserDTO userDTO);

    List<User> search(String string);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void removeUser(Long id);

}
