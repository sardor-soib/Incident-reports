-- Create schema
CREATE SCHEMA IF NOT EXISTS event_report;

-- Use schema-qualified table names
SET search_path TO event_report;

-- Region table
CREATE TABLE IF NOT EXISTS region (
    region_id BIGSERIAL PRIMARY KEY,
    region_name VARCHAR(100) NOT NULL UNIQUE
);

-- Functionality table
CREATE TABLE IF NOT EXISTS functionality (
    functionality_id BIGSERIAL PRIMARY KEY,
    functionality_name VARCHAR(100) NOT NULL UNIQUE
);

-- Group table
CREATE TABLE IF NOT EXISTS functional_group (
    group_id BIGSERIAL PRIMARY KEY,
    group_name VARCHAR(100) NOT NULL UNIQUE,
    user_ids TEXT[] NOT NULL
);

-- Event table
CREATE TABLE IF NOT EXISTS events (
    event_id BIGSERIAL PRIMARY KEY,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    region_id BIGINT,
    functionality_id BIGINT,
    group_id BIGINT,
    CONSTRAINT fk_events_region
        FOREIGN KEY (region_id) REFERENCES region(region_id) ON DELETE SET NULL,
    CONSTRAINT fk_events_functionality
        FOREIGN KEY (functionality_id) REFERENCES functionality(functionality_id) ON DELETE SET NULL,
    CONSTRAINT fk_events_group
        FOREIGN KEY (group_id) REFERENCES functional_group(group_id) ON DELETE SET NULL
);

-- Users table
CREATE TABLE IF NOT EXISTS users (
    user_id BIGSERIAL PRIMARY KEY,
    role VARCHAR(20), -- Discriminator: 'ADMIN', 'USER', 'CONSUMER'
    name VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL
);

