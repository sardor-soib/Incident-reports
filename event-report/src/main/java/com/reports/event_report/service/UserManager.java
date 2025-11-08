package com.reports.event_report.service;

import com.reports.event_report.web.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserManager {

    UserDTO createUser(UserDTO userDTO);

    Page<UserDTO> search(String name, String email, Pageable pageable);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void removeUser(Long id);

}
