package com.ciferz.demo.model;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UsersModel {

    private int id;

    private String name;

    private int phone;

    private String companyName;

    private String companyNameInArabic;

    private String address;

    private String addressInArabic;

    private String vatNo;
}
