package com.reports.event_report.service;

import com.reports.event_report.web.dto.GroupDTO;

import java.util.List;

public interface GroupManager {

    void create(String name);

    List<GroupDTO> search(String name);

    void update(Long id, GroupDTO groupDTO);

    void delete(Long id);
}
