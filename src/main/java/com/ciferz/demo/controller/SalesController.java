package com.ciferz.demo.controller;

import com.ciferz.demo.model.ItemModel;
import com.ciferz.demo.model.SalesModel;
import com.ciferz.demo.reposetries.item.Entity.ItemEntity;
import com.ciferz.demo.reposetries.sales.Entity.SalesEntity;
import com.ciferz.demo.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sales")
public class SalesController {

    @Autowired
    SalesService salesService;

    @GetMapping("/get/all")
    public List<SalesEntity> getall(){
        List<SalesEntity> list = salesService.showAllTheData();
        return list;
    }

    @GetMapping("/get/byId")
    public SalesEntity getById(@PathVariable int id){
        SalesEntity item=salesService.getById(id);
        return item;
    }
    @PostMapping("/Add")
    public ResponseEntity SaveData(@RequestBody List<SalesModel> salesModels){
        return  salesService.AddData(salesModels);
    }

    @PutMapping("/isActive")
    public ResponseEntity updateIsActive(@RequestBody SalesModel salesModel){
        return  salesService.aciveStatus(salesModel);
    }
    @GetMapping("/get/all/userId/{id}")
    public List<SalesEntity> getAllByUserID(@PathVariable int id){
        List<SalesEntity> list = salesService.getAllByUserID(id);
        return list;
    }

    @PostMapping("update/all")
    public ResponseEntity updateAll(@RequestBody SalesModel salesModel){
        return salesService.updateAll(salesModel);
    }

    @PostMapping("deleteBy/{userID}/{salesID}")
    public  ResponseEntity deleteBYItem(@PathVariable("userID") int userID,@PathVariable("salesID") int salesID){

        return salesService.deleteBYSALEsID(userID,salesID);

    }

}
