package com.reports.event_report.service.impl;

import com.reports.event_report.repository.UserRepository;
import com.reports.event_report.service.UserManager;
import com.reports.event_report.service.mapper.UserMapper;
import com.reports.event_report.web.dto.UserDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserManager {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

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
    public Page<UserDTO> search(@NotNull @NotBlank String string, @NotNull Pageable pageable) {
        log.info("Searching users with username containing: {}", string);
        return userMapper.toDTOPage(userRepository.findBynameContainingIgnoreCase(string, pageable));
    }

    @Override
    public UserDTO updateUser(@NotNull Long id, @NotNull UserDTO userDTO) {
        log.info("Updating user with id: {}", id);
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(String.format("User with id %d does not exist", id));
        }
        if (!id.equals(userDTO.id())) {
            throw new IllegalArgumentException(String.format("User id in path %d does not match id in body %d", id, userDTO.id()));
        }
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(userDTO)));
    }

    @Override
    public void removeUser(@NotNull Long id) {
        log.info("Removing user with id: {}", id);
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(String.format("User with id %d does not exist", id));
        }
        userRepository.deleteById(id);
        log.info("User with id {} removed successfully", id);
    }
}
