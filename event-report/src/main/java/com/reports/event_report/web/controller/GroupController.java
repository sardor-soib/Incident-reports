package com.reports.event_report.web.controller;

import com.reports.event_report.service.GroupManager;
import com.reports.event_report.web.dto.GroupDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@Tag(name = "Group Controller", description = "APIs for managing groups")
@RequestMapping("/api/group")
public class GroupController {

    private final GroupManager groupManager;

    @Autowired
    public GroupController(GroupManager groupManager) {
        this.groupManager = groupManager;
    }

    @Operation(summary = "Create a new group", description = "Creates a new group with the provided details")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public GroupDTO createGroup(@RequestBody @Valid GroupDTO groupDTO) {
        return groupManager.createGroup(groupDTO);
    }

    @Operation(summary = "Search groups by name", description = "Retrieves groups that match the provided name")
    @GetMapping("/search")
    public Page<GroupDTO> search(@RequestParam("name") String name, Pageable pageable) {
        return groupManager.search(name, pageable);
    }

    @Operation(summary = "Update an existing group", description = "Updates the details of an existing group identified by its ID")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Group details to update", required = true)
    @PutMapping("/{id}")
    public GroupDTO updateGroup(@PathVariable Long id, @RequestBody @Valid GroupDTO groupDTO) {
        return groupManager.updateGroup(id, groupDTO);
    }

    @Operation(summary = "Delete a group", description = "Deletes the group identified by its ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGroup(@PathVariable Long id) {
        groupManager.deleteGroup(id);
    }
}
