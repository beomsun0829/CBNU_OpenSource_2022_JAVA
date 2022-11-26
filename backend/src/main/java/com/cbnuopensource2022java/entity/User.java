package com.cbnuopensource2022java.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userno;
    private String username;
    private String userid;
    private String userpassword;

    public void setUserName(String username) {
        this.username = username;
    }

    public void setUserId(String userid) {
        this.userid = userid;
    }

    public void setUserPassword(String userpassword) {
        this.userpassword = userpassword;
    }
}