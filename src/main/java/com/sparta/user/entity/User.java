package com.sparta.user.entity;

import com.sparta.common.entity.TimeBase;
import com.sparta.record.entity.ExchangeRecord;
import jakarta.persistence.*;
import lombok.Getter;


import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "users")
public class User extends TimeBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private Boolean isDeleted = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.ROLE_USER;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ExchangeRecord> exchangeRecords = new ArrayList<>();

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void delete() {
        isDeleted = true;
    }

    public void recover() {
        isDeleted = false;
    }

    public void update(String newName, String newPassword) {
        this.name = newName;
        this.password = newPassword;
    }

    public void admin() {
        this.role = Role.ROLE_ADMIN;
    }

    protected User() {
    }

    public enum Role {
        ROLE_USER, ROLE_ADMIN
    }
}