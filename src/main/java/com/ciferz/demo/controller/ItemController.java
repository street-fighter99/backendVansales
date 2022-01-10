package com.ciferz.demo.controller;

import com.ciferz.demo.model.ItemModel;
import com.ciferz.demo.model.SupplierModel;
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

    @GetMapping("/get/byId")
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

}
