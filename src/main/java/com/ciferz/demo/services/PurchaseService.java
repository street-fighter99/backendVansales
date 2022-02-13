package com.ciferz.demo.services;

import com.ciferz.demo.model.PurchaseModel;
import com.ciferz.demo.reposetries.purchase.Entity.PurchaseEntity;
import com.ciferz.demo.reposetries.purchase.PurchaseRepo;
import com.ciferz.demo.reposetries.sales.Entity.SalesEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepo purchaseRepo;

    ModelMapper modelMapper = new ModelMapper();

    public List<PurchaseEntity> showAllTheData() {
        List<PurchaseEntity> list = purchaseRepo.findAllByOrderByIdDesc();
        return  list;
    }

    public PurchaseEntity getById(int id) {
        PurchaseEntity purchaseEntity = purchaseRepo.findById(id).get();
        return purchaseEntity;
    }

    public ResponseEntity AddData(List<PurchaseModel> purchaseModels) {
        List<PurchaseEntity> purchaseEntityList=modelMapper.map(purchaseModels,new TypeToken<List<PurchaseEntity>>(){}.getType());
        purchaseRepo.saveAllAndFlush(purchaseEntityList);
        return new  ResponseEntity("DATA ADDED", HttpStatus.ACCEPTED);
    }

    public List<PurchaseEntity> getAllByUserID(int id) {
        return purchaseRepo.getByUserIdDesc(id);
    }
}

