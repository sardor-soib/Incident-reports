package com.reports.event_report.web.dto;

import java.util.List;

public record GroupDTO(
        Long id,
        String name,
        List<Long> userIds
) {
}
