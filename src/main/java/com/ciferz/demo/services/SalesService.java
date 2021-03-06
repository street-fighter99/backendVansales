package com.ciferz.demo.services;

import com.ciferz.demo.model.SalesModel;
import com.ciferz.demo.reposetries.item.Entity.ItemEntity;
import com.ciferz.demo.reposetries.sales.Entity.SalesEntity;
import com.ciferz.demo.reposetries.sales.SalesRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService {

    @Autowired
    SalesRepo salesRepo;

    ModelMapper modelMapper = new ModelMapper();


    public List<SalesEntity> showAllTheData() {
        List<SalesEntity> list = salesRepo.findAllByOrderByIdDesc();
        return  list;
    }

    public SalesEntity getById(int id) {
        SalesEntity salesEntity = salesRepo.getById(id);
        return salesEntity;
    }

    public ResponseEntity AddData(List<SalesModel> salesModels) {
        List<SalesEntity> salesEntityList=modelMapper.map(salesModels,new TypeToken<List<SalesEntity>>(){}.getType());
        salesRepo.saveAllAndFlush(salesEntityList);
        return new  ResponseEntity("DATA ADDED", HttpStatus.ACCEPTED);

    }

    public ResponseEntity aciveStatus(SalesModel salesModel) {
        salesRepo.update(salesModel.getId(),salesModel.getIsactive());
        return new ResponseEntity("UPDATED",HttpStatus.ACCEPTED);

    }

    public List<SalesEntity> getAllByUserID(int id) {
        return salesRepo.getByUserIdDesc(id);
    }

    public ResponseEntity updateAll(SalesModel salesModel) {
        SalesEntity sales = salesRepo.updateAll(salesModel.getSaleId(),salesModel.getCustomerId(),salesModel.getItemList()
                ,salesModel.getTotalAmount(),salesModel.getDiscount(),salesModel.getAftDiscount(),salesModel.getNetAmount(),salesModel.getRecievedAmount()
                ,salesModel.getBalance(),salesModel.getTotalBalance(),salesModel.getIsactive(),salesModel.getVat(),salesModel.getTdate(),salesModel.getUserId());

        return new ResponseEntity(sales,HttpStatus.ACCEPTED);
    }

    public ResponseEntity deleteBYSALEsID(int userID, int salesID) {
        salesRepo.DELBYDSALID(userID,salesID);
        return new ResponseEntity("SALES DELETED SUCCESSFULLY.",HttpStatus.ACCEPTED);
    }
}
