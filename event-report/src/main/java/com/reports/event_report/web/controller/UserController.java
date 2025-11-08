package com.reports.event_report.web.controller;

import com.reports.event_report.service.UserManager;
import com.reports.event_report.web.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@Tag(name = "User Controller", description = "APIs for managing users")
@RequestMapping("/api/user")
public class UserController {

    private final UserManager userManager;

    @Autowired
    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @Operation(summary = "Create a new user", description = "Creates a new user with the provided details")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userManager.createUser(userDTO);
    }

    @Operation(summary = "Search users by name and email", description = "Retrieves users that match the provided name and email")
    @GetMapping("/search")
    public Page<UserDTO> search(
            @RequestParam(name = "name", required = false) String username,
            @RequestParam(name = "email", required = false) String email,
            Pageable pageable) {
        return userManager.search(username, email, pageable);
    }

    @Operation(summary = "Update an existing user", description = "Updates the details of an existing user identified by its ID")
    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userManager.updateUser(id, userDTO);
    }

    @Operation(summary = "Delete a user", description = "Deletes the user identified by its ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeUser(@PathVariable Long id) {
        userManager.removeUser(id);
    }
}
