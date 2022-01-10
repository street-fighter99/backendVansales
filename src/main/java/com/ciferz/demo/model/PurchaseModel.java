package com.ciferz.demo.model;
import lombok.Data;

@Data
public class PurchaseModel {

    private int id;

    private int supplierId;

    private String itemList;

    private double totalAmount;

    private double discount;

    private double netAmount;

    private double paidAmount;

    private double balance;

    private double totalBalance;

    private double vatNo;
}
