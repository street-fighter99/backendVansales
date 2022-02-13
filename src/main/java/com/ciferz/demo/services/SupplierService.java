package com.ciferz.demo.services;

import com.ciferz.demo.model.SupplierModel;
import com.ciferz.demo.reposetries.Customer.Entity.CustomerEntity;
import com.ciferz.demo.reposetries.supplier.Entity.SupplierEntity;
import com.ciferz.demo.reposetries.supplier.SupplierRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    SupplierRepo supplierRepo;


    private final ModelMapper modelMapper=new ModelMapper();

    public ResponseEntity AddData(List<SupplierModel> supplierModel) {
        List<SupplierEntity> supplierEntity=modelMapper.map(supplierModel,new TypeToken<List<SupplierEntity>>(){}.getType());
        supplierRepo.saveAllAndFlush(supplierEntity);
        return new  ResponseEntity("DATA ADDED", HttpStatus.ACCEPTED);

    }

    public List<SupplierEntity> AllTheSupplier() {
        List<SupplierEntity> list = supplierRepo.findAll();
        return list;
    }

    public SupplierEntity getById(int id) {
        SupplierEntity supplierEntity=supplierRepo.findById(id).get();
        return supplierEntity;
    }

    public ResponseEntity aciveStatus(int id) {
        SupplierEntity supplierEntity=supplierRepo.findById(id).get();
        supplierRepo.update(id,supplierEntity.getIsactive() == 1 ? 0 : 1);
        return new ResponseEntity("UPDATED",HttpStatus.ACCEPTED);
    }

    public List<SupplierEntity> getActiveCustomer(int id) {
        List<SupplierEntity> list = supplierRepo.getByactiveSuppById(id);
        return list;
    }

    public ResponseEntity updateBal(SupplierModel supplierModel) {
        supplierRepo.updateBal(supplierModel.getId(),supplierModel.getCbalance());

        return new ResponseEntity("Balance updated",HttpStatus.ACCEPTED);
    }

    public List<SupplierEntity> getAllByUserID(int id) {
        return supplierRepo.getByUserId(id);
    }
}
