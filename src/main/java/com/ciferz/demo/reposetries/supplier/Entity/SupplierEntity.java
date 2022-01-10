package com.ciferz.demo.reposetries.supplier.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "supplier")
public class SupplierEntity {

    @Id
    @Column(name = "supp_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "vat_no")
    private String vatNo;

    @Column(name = "cbalance")
    private double cbalance;

    @Column(name = "is_active")
    private int isactive;
}

