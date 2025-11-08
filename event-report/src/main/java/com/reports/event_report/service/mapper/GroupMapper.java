package com.reports.event_report.service.mapper;

import com.reports.event_report.repository.entity.Group;
import com.reports.event_report.repository.entity.User;
import com.reports.event_report.web.dto.GroupDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    @Mapping(target = "users", source = "userIds")
    Group toEntity(GroupDTO groupDTO);

    @Mapping(target = "userIds", source = "users")
    GroupDTO toDTO(Group group);

    List<Group> toEntityList(List<GroupDTO> groupDTOs);

    List<GroupDTO> toDTOList(List<Group> groups);

    default Page<GroupDTO> toDTOPage(Page<Group> page) {
        List<GroupDTO> dtoList = toDTOList(page.getContent());
        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }

    default List<User> map(List<Long> ids) {
        if (ids == null) return null;
        return ids.stream().map(id -> {
            User u = new User();
            u.setId(id);
            return u;
        }).collect(Collectors.toList());
    }

    default List<Long> mapUsersToIds(List<User> users) {
        if (users == null) return null;
        return users.stream()
                .map(User::getId)
                .collect(Collectors.toList());
    }

}
