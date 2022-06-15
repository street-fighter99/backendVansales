package com.ciferz.demo.model;

import lombok.Data;

@Data
public class StockChk {
    private int id;
    private String name;
    private int stock;
    private double price;
    private double sprice;
}
