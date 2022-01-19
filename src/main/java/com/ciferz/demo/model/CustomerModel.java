package com.ciferz.demo.model;

import lombok.Data;

@Data
public class CustomerModel {

    private int id;
    private String name;
    //private String aname;
    private String address;
    //private String aaddress;
    private String vatNo;
    private int isactive;
    private double cbalance;


}
