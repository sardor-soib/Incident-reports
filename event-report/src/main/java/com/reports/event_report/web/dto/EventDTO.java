package com.reports.event_report.web.dto;

import java.util.List;

public record EventDTO(

        Long id,
        String startTime,
        String endTime,
        String type,
        Long regionId,
        Long functionalityId,
        Integer category,
        Integer impact,
        String description,
        String solution,
        List<Long> responsibleGroupIds
) {
}
