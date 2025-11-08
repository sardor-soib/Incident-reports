package com.reports.event_report.repository.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "functional_group")
public class Group {

    @OneToMany(mappedBy = "group")
    List<User> users;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long groupId;
    @Column(name = "group_name")
    private String groupName;

    public Group() {
    }

    public Group(Long groupId, String groupName, List<User> users) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.users = users;
    }

    public Long getId() {
        return groupId;
    }

    public void setId(Long id) {
        this.groupId = id;
    }

    public String getName() {
        return groupName;
    }

    public void setName(String name) {
        this.groupName = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
