package com.cbnuopensource2022java.entity;

import javax.persistence.Entity;
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
    private int id; // wfcltId
    private String name; // faclNm
    private String tycode; // faclTyCd
    private String address; // lcMnad
    private String statediv; // salStaDivCd
    private String lat; // faclLat
    private String lng; // faclLng
}

/*
 * Columns:
 * Id int PK
 * Name varchar(100)
 * TyCode varchar(45)
 * Address varchar(100)
 * StateDiv varchar(10)
 * Lat float
 * Lng float
 */