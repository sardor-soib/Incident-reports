package com.reports.event_report.web.controller;

import com.reports.event_report.service.FunctionalityManager;
import com.reports.event_report.web.dto.FunctionalityDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@Tag(name = "Functionality Controller", description = "APIs for managing functionalities")
@RequestMapping("/api/functionality")
public class FunctionalityController {

    private final FunctionalityManager functionalityManager;

    @Autowired
    public FunctionalityController(FunctionalityManager functionalityManager) {
        this.functionalityManager = functionalityManager;
    }

    @Operation(summary = "Create a new functionality", description = "Creates a new functionality with the provided details")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public FunctionalityDTO createFunctionality(@RequestBody @Valid FunctionalityDTO functionalityDTO) {
        return functionalityManager.createFunctionality(functionalityDTO);
    }

    @Operation(summary = "Search functionalities by name", description = "Retrieves functionalities that match the provided name")
    @GetMapping("/search")
    public List<FunctionalityDTO> search(@RequestParam("name") String name) {
        return functionalityManager.search(name);
    }

    @Operation(summary = "Update an existing functionality", description = "Updates the details of an existing functionality identified by its ID")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Functionality details to update", required = true)
    @PutMapping("/{id}")
    public FunctionalityDTO updateFunctionality(@PathVariable Long id, @RequestBody @Valid FunctionalityDTO functionalityDTO) {
        return functionalityManager.updateFunctionality(id, functionalityDTO);
    }

    @Operation(summary = "Delete a functionality", description = "Deletes the functionality identified by its ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFunctionality(@PathVariable Long id) {
        functionalityManager.deleteFunctionality(id);
    }
}
