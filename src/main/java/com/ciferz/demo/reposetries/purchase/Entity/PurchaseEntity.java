package com.ciferz.demo.reposetries.purchase.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "purchase")
public class PurchaseEntity {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "supplier_id")
    private int supplierId;

    @Column(name = "item_list")
    private String itemList;


    @Column(name = "total_amount")
    private double totalAmount;


    @Column(name = "discount")
    private double discount;

    @Column(name = "aftdiscount")
    private double aftDiscount;

    @Column(name = "net_amount")
    private double netAmount;

    @Column(name = "paid_amount")
    private double paidAmount;

    @Column(name = "balance")
    private double balance;

    @Column(name = "total_balance")
    private double totalBalance;

    @Column(name = "vat")
    private String vat;

    @Column(name = "p_date")
    private Date tdate;


}
