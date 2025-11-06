package com.reports.event_report.repository;

import org.springframework.stereotype.Component;

public enum EventType {
    EMERGENCY("Emergency"),
    RFC("RFC"),
    MAINTENANCE("Maintenance"),
    INCIDENT("Incident");

    private final String displayName;

    EventType(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return this.displayName;
    }
}
