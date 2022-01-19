package com.ciferz.demo.services;

import com.ciferz.demo.model.ItemModel;
import com.ciferz.demo.model.SupplierModel;
import com.ciferz.demo.reposetries.item.Entity.ItemEntity;
import com.ciferz.demo.reposetries.item.ItemRepo;
import com.ciferz.demo.reposetries.supplier.Entity.SupplierEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    ItemRepo itemRepo;

    ModelMapper modelMapper = new ModelMapper();

    public List<ItemEntity> showAllTheData() {
        List<ItemEntity> list = itemRepo.findAll();
        return list;
    }



    public ResponseEntity AddData(List<ItemModel> itemModels) {
        List<ItemEntity> itemEntities=modelMapper.map(itemModels,new TypeToken<List<ItemEntity>>(){}.getType());
        itemRepo.saveAllAndFlush(itemEntities);
        return new  ResponseEntity("DATA ADDED", HttpStatus.ACCEPTED);
    }

    public ResponseEntity aciveStatus(ItemModel itemModel) {
        itemRepo.update(itemModel.getId(),itemModel.getIsactive());
        return new ResponseEntity("UPDATED",HttpStatus.ACCEPTED);
    }

    public ItemEntity getById(int id) {
        ItemEntity itemEntity = itemRepo.getById(id);
        return itemEntity;
    }

    public ResponseEntity updateStocks(ItemModel itemModel) {
        ItemEntity itemEntity = itemRepo.getById(itemModel.getId());
        itemRepo.updateStocks(itemModel.getId(),itemEntity.getStock()-itemModel.getStock());
        return new ResponseEntity("stock is updated",HttpStatus.ACCEPTED);
    }
}
