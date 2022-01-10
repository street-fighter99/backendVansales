package com.ciferz.demo.reposetries.item.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
public class ItemEntity {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "arabic_name")
    private String arabicname;

    @Column(name = "suppliers")
    private String suppliers;

    @Column(name = "stock")
    private int stock;

    @Column(name = "vat")
    private double vat;

    @Column(name = "is_active")
    private int isactive;
}
