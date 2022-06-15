package com.ciferz.demo.controller;

import com.ciferz.demo.model.ItemModel;
import com.ciferz.demo.model.SupplierModel;
import com.ciferz.demo.reposetries.Customer.Entity.CustomerEntity;
import com.ciferz.demo.reposetries.item.Entity.ItemEntity;
import com.ciferz.demo.reposetries.supplier.Entity.SupplierEntity;
import com.ciferz.demo.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("item")
public class ItemController {

    @Autowired
    ItemService itemService;


    @GetMapping("/get/all")
    public List<ItemEntity> getall(){
        List<ItemEntity> list = itemService.showAllTheData();
        return list;
    }

    @GetMapping("/get/byId/{id}")
    public ItemEntity getById(@PathVariable int id){
        ItemEntity item=itemService.getById(id);
        return item;
    }
    @PostMapping("/Add")
    public ResponseEntity SaveData(@RequestBody List<ItemModel> itemModels){
        return  itemService.AddData(itemModels);
    }

    @PutMapping("/isActive")
    public ResponseEntity updateIsActive(@RequestBody ItemModel itemModel){
        return  itemService.aciveStatus(itemModel);
    }

    @PostMapping("update/substock")
    public  ResponseEntity updateSubStocks(@RequestBody ItemModel itemModel){
        return itemService.updateSubStocks(itemModel);
    }

    @PostMapping("update/addstock")
    public  ResponseEntity updateAddStocks(@RequestBody ItemModel itemModel){
        return itemService.updateAddStocks(itemModel);
    }

    @PostMapping("update/All")
    public  ResponseEntity updateAll(@RequestBody ItemModel itemModel){
        return itemService.updateAll(itemModel);
    }

    @PostMapping("deleteBy/{userId}/{itemId}")
    public  ResponseEntity deleteByuserIDandItemID(@PathVariable("userId") int userId,@PathVariable("itemId") int itemId){
        return itemService.deleteBy(userId,itemId);
    }


    @GetMapping("/get/all/userId/{id}")
    public List<ItemEntity> getAllByUserID(@PathVariable int id){
        List<ItemEntity> list = itemService.getAllByUserID(id);
        return list;
    }

}
