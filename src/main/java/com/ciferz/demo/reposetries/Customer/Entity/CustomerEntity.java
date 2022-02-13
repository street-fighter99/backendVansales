package com.ciferz.demo.reposetries.Customer.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

//    @Column(name = "aname")
//    private String aname;

    @Column(name = "address")
    private String address;

//    @Column(name = "aaddress")
//    private String aaddress;

    @Column(name = "vat_no")
    private String vatNo;

    @Column(name = "cbalance")
    private double cbalance;

    @Column(name = "is_active")
    private int isactive;

    @Column(name = "user_id")
    private int userId;

}
