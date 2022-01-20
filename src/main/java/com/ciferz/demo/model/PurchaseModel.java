package com.ciferz.demo.model;
import lombok.Data;

import java.util.Date;

@Data
public class PurchaseModel {

    private int id;

    private int supplierId;

    private String itemList;

    private double totalAmount;

    private double discount;

    private double aftDiscount;

    private double netAmount;

    private double paidAmount;

    private double balance;

    private double totalBalance;

    private double vat;

    private Date tdate;
}
