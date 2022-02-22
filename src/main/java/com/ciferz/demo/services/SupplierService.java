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
//        SupplierEntity supplierEntitys = supplierRepo.findBySuppId(supplierModel.get(0).getId());
        SupplierEntity supplierEntitys = getById(supplierModel.get(0).getId());
        if (supplierEntitys != null){

            return new ResponseEntity("DATA IS ALREADY THERE.", HttpStatus.CONFLICT);

        } else {

            List<SupplierEntity> supplierEntity = modelMapper.map(supplierModel, new TypeToken<List<SupplierEntity>>() {
            }.getType());
            supplierRepo.saveAllAndFlush(supplierEntity);
            return new ResponseEntity("DATA ADDED.", HttpStatus.ACCEPTED);

        }
    }

    public List<SupplierEntity> AllTheSupplier() {
        List<SupplierEntity> list = supplierRepo.findAll();
        return list;
    }

    public SupplierEntity getById(int id) {
        return supplierRepo.findById(id).isPresent()?supplierRepo.findById(id).get():null;
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

    public ResponseEntity updateAllCol(SupplierModel supplierModel) {
        SupplierEntity supplier = supplierRepo.updateAllCol(supplierModel.getSuppId(),supplierModel.getName()
        ,supplierModel.getAddress(),supplierModel.getVatNo(),supplierModel.getCbalance(),supplierModel.getIsactive()
        ,supplierModel.getUserId());

        return new ResponseEntity(supplier,HttpStatus.ACCEPTED);
    }

    public ResponseEntity deleteBySuppID(int suppID, int userID) {
        supplierRepo.deleteBYSuppID(suppID,userID);
        return new ResponseEntity("data has beem deleted successfully",HttpStatus.ACCEPTED);
    }
}
