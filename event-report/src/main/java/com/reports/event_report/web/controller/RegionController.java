package com.reports.event_report.web.controller;

import com.reports.event_report.service.RegionManager;
import com.reports.event_report.web.dto.RegionDTO;
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
@Tag(name = "Region Controller", description = "APIs for managing regions")
@RequestMapping("/api/region")
public class RegionController {

    private final RegionManager regionManager;

    @Autowired
    public RegionController(RegionManager regionManager) {
        this.regionManager = regionManager;
    }


    @Operation(summary = "Create a new region", description = "Creates a new region with the provided details")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RegionDTO createRegion(RegionDTO regionDTO) {
        return regionManager.createRegion(regionDTO);
    }

    @Operation(summary = "Search regions by name", description = "Retrieves regions that match the provided name")
    @GetMapping("/search")
    public Page<RegionDTO> search(String name, Pageable pageable) {
        return regionManager.search(name, pageable);
    }

    @Operation(summary = "Update an existing region", description = "Updates the details of an existing region identified by its ID")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Region details to update", required = true)
    @PutMapping("/{id}")
    public RegionDTO updateRegion(@PathVariable Long id, RegionDTO regionDTO) {
        return regionManager.updateRegion(id, regionDTO);
    }

    @Operation(summary = "Delete a region", description = "Deletes the region identified by its ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRegion(@PathVariable Long id) {
        regionManager.deleteRegion(id);
    }
}
