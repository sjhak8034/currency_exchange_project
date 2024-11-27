package com.sparta.user.entity;

import com.sparta.common.entity.TimeBase;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "user")
public class User extends TimeBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private String password;

    @Column
    private boolean is_Deleted = Boolean.FALSE;


    @OneToMany(mappedBy = "exchange_record_id")
    private List<ExchangeRecord> exchangeRecords = new ArrayList<>();


    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void delete() {
        is_Deleted = true;
    }

    public void recover() {
        is_Deleted = false;
    }

    public void update(String newName, String newPassword) {
        this.name = newName;
        this.password = newPassword;
    }

    public User() {}
}