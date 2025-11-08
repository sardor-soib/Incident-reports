package com.reports.event_report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = "com.reports.event_report")
@EnableJpaRepositories("com.reports.event_report.repository")
public class EventReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventReportApplication.class, args);
    }

}
