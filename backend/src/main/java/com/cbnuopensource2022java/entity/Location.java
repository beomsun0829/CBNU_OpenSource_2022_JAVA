package com.cbnuopensource2022java.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String tycode;
    private String address;
    private int statediv;
    private String lat;
    private String lng;
}

/*
 * Columns:
 * Id int PK
 * Name varchar(100)
 * TyCode varchar(45)
 * Address varchar(100)
 * StateDiv int
 * Lat float
 * Lng float
 */