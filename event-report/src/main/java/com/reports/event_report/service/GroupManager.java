package com.reports.event_report.service;

import com.reports.event_report.web.dto.GroupDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupManager {

    GroupDTO createGroup(GroupDTO groupDTO);

    Page<GroupDTO> search(String name, Pageable pageable);

    GroupDTO updateGroup(Long id, GroupDTO groupDTO);

    void deleteGroup(Long id);
}
