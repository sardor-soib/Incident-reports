package com.reports.event_report.repository;

public enum Role {
    CONSUMER("Consumer"),
    ADMIN("Admin"),
    USER("User");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return this.displayName;
    }
}
