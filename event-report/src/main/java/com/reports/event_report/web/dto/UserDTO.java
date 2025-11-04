package com.reports.event_report.web.dto;

public record UserDTO(
    Long id,
    String name,
    String email,
    String role,
    Long groupId
) {
}
