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
        ItemEntity itemEntity = itemRepo.getByIds(itemModels.get(0).getId());
        if (itemEntity!=null){
            return new  ResponseEntity("DATA IS ALREADY THERE.", HttpStatus.ACCEPTED);
        }
        else{

        List<ItemEntity> itemEntities=modelMapper.map(itemModels,new TypeToken<List<ItemEntity>>(){}.getType());
        itemRepo.saveAllAndFlush(itemEntities);
        return new  ResponseEntity("DATA ADDED.", HttpStatus.ACCEPTED);
    }
    }

    public ResponseEntity aciveStatus(ItemModel itemModel) {
        itemRepo.update(itemModel.getId(),itemModel.getIsactive());
        return new ResponseEntity("UPDATED",HttpStatus.ACCEPTED);
    }

    public ItemEntity getById(int id) {
        ItemEntity itemEntity = itemRepo.findById(id).get();
        return itemEntity;
    }

    public ResponseEntity updateSubStocks(ItemModel itemModel) {
        ItemEntity itemEntity = itemRepo.getByitemIdAndUserID(itemModel.getItemId(),itemModel.getUserId());
        itemRepo.updateStocks(itemModel.getItemId(),itemEntity.getStock()-itemModel.getStock(),itemModel.getUserId());
        return new ResponseEntity("stock is updated",HttpStatus.ACCEPTED);
    }

    public ResponseEntity updateAddStocks(ItemModel itemModel) {
        ItemEntity itemEntity = itemRepo.getByitemIdAndUserID(itemModel.getItemId(),itemModel.getUserId());
        itemRepo.updateStocks(itemModel.getItemId(),itemEntity.getStock()+itemModel.getStock(),itemModel.getUserId());
        return new ResponseEntity("stock is updated",HttpStatus.ACCEPTED);
    }

    public ResponseEntity updateAll(ItemModel itemModel) {

        itemRepo.updateAll(itemModel.getName(),itemModel.getIsactive(),itemModel.getStock(),itemModel.getSuppliers(),itemModel.getVat(),itemModel.getItemId(),itemModel.getArabicname(),itemModel.getUserId());
        return new ResponseEntity("Item details are updated.",HttpStatus.ACCEPTED);

    }

    public List<ItemEntity> getAllByUserID(int id) {
        return itemRepo.getByUserId(id);
    }

    public ResponseEntity deleteBy(int userId, int itemId) {

        itemRepo.deleteByUsrandItemID(userId,itemId);
        return new ResponseEntity("DATA HAS BEEN DELETED.",HttpStatus.ACCEPTED);

    }
}
