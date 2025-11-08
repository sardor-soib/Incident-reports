package com.reports.event_report.web.controller;

import com.reports.event_report.service.GroupManager;
import com.reports.event_report.web.dto.GroupDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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


}
