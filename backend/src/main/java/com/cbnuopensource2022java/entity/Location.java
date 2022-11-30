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
    private int wfcltId; // wfcltId
    private String faclNm; // faclNm
    private String faclTyCd; // faclTyCd
    private String lcMnad; // lcMnad
    private String salStaDivCd; // salStaDivCd
    private String faclLat; // faclLat
    private String faclLng; // faclLng
}
