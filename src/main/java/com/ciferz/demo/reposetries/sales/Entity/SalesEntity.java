package com.ciferz.demo.reposetries.sales.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "sales")
public class SalesEntity {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "customer_id")
    private int customerId;

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

    @Column(name = "recieved_amount")
    private double recievedAmount;

    @Column(name = "balance")
    private double balance;

    @Column(name = "total_balance")
    private double totalBalance;

    @Column(name = "is_active")
    private int isactive;

    @Column(name = "vat")
    private double vat;

    @Column(name = "s_date")
    private Date sDate;

}
