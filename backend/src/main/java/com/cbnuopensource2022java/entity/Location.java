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
    private String Name;
    private String TyCode;
    private String Address;
    private int StateDiv;
    private String Lat;
    private String Lng;
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