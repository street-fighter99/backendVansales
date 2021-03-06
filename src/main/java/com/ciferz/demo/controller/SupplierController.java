package com.ciferz.demo.controller;

import com.ciferz.demo.model.CustomerModel;
import com.ciferz.demo.model.SupplierModel;
import com.ciferz.demo.reposetries.Customer.Entity.CustomerEntity;
import com.ciferz.demo.reposetries.supplier.Entity.SupplierEntity;
import com.ciferz.demo.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;


    @GetMapping("/get/all")
    public List<SupplierEntity> getall(){
        List<SupplierEntity> list = supplierService.AllTheSupplier();
        return list;
    }

    @GetMapping("/get/byId/{id}")
    public SupplierEntity getById(@PathVariable int id){
        SupplierEntity supplierEntity=supplierService.getById(id);
        return supplierEntity;
    }
    @PostMapping("/Add")
    public ResponseEntity SaveData(@RequestBody List<SupplierModel> supplierModel){

        return  supplierService.AddData(supplierModel);

    }

    @PutMapping("/isActive/{id}")
    public ResponseEntity updateIsActive(@PathVariable int id){
        return  supplierService.aciveStatus(id);
    }

    @GetMapping("/get/all/active/{id}")
    public List<SupplierEntity> getAllActiveCustomer(@PathVariable int id){
        List<SupplierEntity> list = supplierService.getActiveCustomer(id);
        return list;
    }
    @PostMapping("/update/balance")
    public ResponseEntity updateBal(@RequestBody SupplierModel supplierModel){

        return supplierService.updateBal(supplierModel);
    }

    @GetMapping("/get/all/userId/{id}")
    public List<SupplierEntity> getAllByUserID(@PathVariable int id){
        List<SupplierEntity> list = supplierService.getAllByUserID(id);
        return list;
    }

    @PostMapping("/update/all")
    public ResponseEntity updateAllCol(@RequestBody SupplierModel supplierModel){

        return supplierService.updateAllCol(supplierModel);
    }

    @PostMapping("deleteBy/{suppID}/{userID}")
    public ResponseEntity deleteBySuppID(@PathVariable("suppID") int suppID, @PathVariable("userID") int userID){

        return supplierService.deleteBySuppID(suppID,userID);
    }
}
