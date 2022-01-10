package com.ciferz.demo.controller;

import com.ciferz.demo.model.PurchaseModel;
import com.ciferz.demo.model.SalesModel;
import com.ciferz.demo.reposetries.purchase.Entity.PurchaseEntity;
import com.ciferz.demo.reposetries.sales.Entity.SalesEntity;
import com.ciferz.demo.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("purchase")
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;


    @GetMapping("/get/all")
    public List<PurchaseEntity> getall(){
        List<PurchaseEntity> list = purchaseService.showAllTheData();
        return list;
    }

    @GetMapping("/get/byId/{id}")
    public PurchaseEntity getById(@PathVariable int id){
        PurchaseEntity item=purchaseService.getById(id);
        return item;
    }
    @PostMapping("/Add")
    public ResponseEntity SaveData(@RequestBody List<PurchaseModel> purchaseModels){
        return  purchaseService.AddData(purchaseModels);
    }

}
