package com.reports.event_report.config;

import com.reports.event_report.web.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {
    private final Long id;

    public CustomUserDetails(UserDTO user, Collection<? extends GrantedAuthority> authorities) {
        super(user.email(), user.password(), authorities);
        this.id = user.id();
    }

    public Long getId() {
        return id;
    }
}
