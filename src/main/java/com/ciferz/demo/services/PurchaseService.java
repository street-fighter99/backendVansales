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

    public ResponseEntity updateAll(PurchaseModel pModel) {
        PurchaseEntity purchaseEntity = purchaseRepo.updateAll(pModel.getId(),pModel.getSupplierId(),pModel.getItemList()
        ,pModel.getTotalAmount(),pModel.getDiscount(),pModel.getAftDiscount(),pModel.getNetAmount(),pModel.getPaidAmount()
        ,pModel.getBalance(),pModel.getTotalBalance(),pModel.getVat(),pModel.getTdate(),pModel.getUserId());
        return new ResponseEntity(purchaseEntity,HttpStatus.ACCEPTED);
    }

    public ResponseEntity deleteBYPURID(int userID, int purchaseID) {
        PurchaseEntity purchase = purchaseRepo.delBYPURID(userID,purchaseID);
        return new ResponseEntity(purchase,HttpStatus.ACCEPTED);
    }
}

