#!/bin/bash
set -e

# Create database if it does not exist
psql -v ON_ERROR_STOP=1 --username "postgres" --dbname "event_report" <<-EOSQL
    DO \$\$
    BEGIN
        IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'event_report') THEN
            CREATE DATABASE event_report;
        END IF;
    END
    \$\$;
EOSQL

# Create user and grant privileges
psql -v ON_ERROR_STOP=1 --username "postgres" --dbname "event_report" <<-EOSQL
    CREATE USER postgres WITH ENCRYPTED PASSWORD '12345';
    GRANT ALL PRIVILEGES ON DATABASE event_report TO postgres;
EOSQL