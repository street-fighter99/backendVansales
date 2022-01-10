package com.ciferz.demo.model;

import lombok.Data;

@Data
public class SalesModel {

    private int id;

    private int customerId;

    private String itemList;

    private double totalAmount;

    private double discount;

    private double aftDiscount;

    private double netAmount;

    private double recievedAmount;

    private double balance;

    private double totalBalance;

    private int isactive;

    private double vat;


}
