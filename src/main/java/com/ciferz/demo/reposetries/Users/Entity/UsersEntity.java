package com.ciferz.demo.reposetries.Users.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "users")
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_name_in_arabic")
    private String companyNameInArabic;

    @Column(name = "company_id")
    private int companyId;

    @Column(name = "address")
    private String address;

    @Column(name = "address_in_arabic")
    private String addressInArabic;

    @Column(name = "vat_no")
    private String vatNo;

    @Column(name = "is_active")
    private int isActive;


}
