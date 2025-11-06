package com.reports.event_report.service;

import com.reports.event_report.web.dto.GroupDTO;

import java.util.List;

public interface GroupManager {

    GroupDTO createGroup(String name);

    List<GroupDTO> search(String name);

    GroupDTO updateGroup(Long id, GroupDTO groupDTO);

    void deleteGroup(Long id);
}
