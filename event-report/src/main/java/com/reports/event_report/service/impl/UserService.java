package com.reports.event_report.service.impl;

import com.reports.event_report.repository.UserRepository;
import com.reports.event_report.repository.entity.User;
import com.reports.event_report.service.UserManager;
import com.reports.event_report.service.mapper.UserMapper;
import com.reports.event_report.web.dto.UserDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserManager {

    private final Logger log = org.slf4j.LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO createUser(@NotNull UserDTO userDTO) {
        log.info("Adding user: {}", userDTO);
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(userDTO)));
    }

    @Override
    public List<User> search(@NotNull @NotBlank String string) {
        log.info("Searching users with username containing: {}", string);
        return userRepository.findByUsernameContainingIgnoreCase(string);
    }

    @Override
    public UserDTO updateUser(@NotNull Long id, @NotNull UserDTO userDTO) {
        log.info("Updating user with id: {}", id);
        if (!userRepository.existsById(id)) {
            log.error("User with id {} does not exist", id);
            throw new IllegalArgumentException(String.format("User with id %d does not exist", id));
        }
        if (!id.equals(userDTO.id())) {
            log.error("User id in path {} does not match id in body {}", id, userDTO.id());
            throw new IllegalArgumentException(String.format("User id in path %d does not match id in body %d", id, userDTO.id()));
        }
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(userDTO)));
    }

    @Override
    public void removeUser(@NotNull Long id) {
        log.info("Removing user with id: {}", id);
        if (!userRepository.existsById(id)) {
            log.error("User with id {} does not exist", id);
            throw new IllegalArgumentException(String.format("User with id %d does not exist", id));
        }
        userRepository.deleteById(id);
        log.info("User with id {} removed successfully", id);
    }
}
