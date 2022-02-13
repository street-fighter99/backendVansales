package com.ciferz.demo.controller;


import com.ciferz.demo.model.CustomerModel;
import com.ciferz.demo.reposetries.Customer.Entity.CustomerEntity;
import com.ciferz.demo.reposetries.supplier.Entity.SupplierEntity;
import com.ciferz.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

@GetMapping("/get/all")
    public List<CustomerEntity> getall(){
    List<CustomerEntity> list = customerService.getAll();
    return list;
}

    @GetMapping("/get/byId/{id}")
    public CustomerEntity getById(@PathVariable int id){
        CustomerEntity customerEntity=customerService.getById(id);
        return customerEntity;
    }

@PostMapping("/Add")
    public ResponseEntity saveAllData(@RequestBody List<CustomerModel> customerModels){
    return  customerService.addDatas(customerModels);
}

    @PostMapping("/isActive/{id}")
    public ResponseEntity updateIsActive(@PathVariable int id){
        return  customerService.aciveStatus(id);
    }

    @PostMapping("/update/balance")
    public ResponseEntity updateBal(@RequestBody CustomerModel customerModel){

    return customerService.updateBal(customerModel);
    }


    @PostMapping("/update/all")
    public ResponseEntity updateAll(@RequestBody CustomerModel customerModel){

        return customerService.updateAll(customerModel);
    }

    @GetMapping("/get/all/active")
    public List<CustomerEntity> getAllActiveCustomer(){
        List<CustomerEntity> list = customerService.getActiveCustomer();
        return list;
    }

    @GetMapping("/get/all/userId/{id}")
    public List<CustomerEntity> getAllByUserID(@PathVariable int id){
        List<CustomerEntity> list = customerService.getAllByUserID(id);
        return list;
    }

}

