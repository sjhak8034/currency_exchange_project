package com.sparta.user.entity;

import com.sparta.common.entity.TimeBase;
import com.sparta.record.entity.ExchangeRecord;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

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

    @Column(nullable = false)
    private String password;

    @Column
    private Boolean isDeleted = false;


    @OneToMany(mappedBy = "user")
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

    public User() {}
}