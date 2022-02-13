package com.ciferz.demo.model;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UsersModel {

    private int id;

    private String name;

    private String phone;

    private String companyName;

    private String companyNameInArabic;

    private int companyId;

    private String address;

    private String addressInArabic;

    private String vatNo;

    private int isActive;
}
