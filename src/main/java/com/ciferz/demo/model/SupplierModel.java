package com.ciferz.demo.model;

import lombok.Data;

import javax.persistence.Column;

@Data
public class SupplierModel {

    private int id;
    private int suppId;
    private String name;
    private String address;
    private String vatNo;
    private double cbalance;
    private int isactive;
    private int userId;

}
