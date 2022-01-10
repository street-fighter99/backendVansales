package com.ciferz.demo.model;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ItemModel {

    private int id;

    private String name;

    private String arabicname;

    private String suppliers;

    private int stock;

    private double vat;

    private int isactive;

}
