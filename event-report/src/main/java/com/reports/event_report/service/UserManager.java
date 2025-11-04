package com.reports.event_report.service;

import com.reports.event_report.repository.entity.User;
import com.reports.event_report.web.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserManager {

    void addUser(UserDTO userDTO);

    List<User> search(String string);

    void updateUser(Long id, UserDTO userDTO);

    void removeUser(Long id);

}
