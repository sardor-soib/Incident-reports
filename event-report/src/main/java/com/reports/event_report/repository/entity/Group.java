package com.reports.event_report.repository.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "group")
public class Group {

    @OneToMany(mappedBy = "group")
    List<User> users;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;
    @Column(name = "name")
    private String name;

    public Group() {
    }

    public Group(Long id, String name, List<User> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
