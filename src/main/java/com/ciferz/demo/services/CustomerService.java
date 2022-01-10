package com.ciferz.demo.services;

import com.ciferz.demo.model.CustomerModel;
import com.ciferz.demo.reposetries.Customer.CustomerRepo;
import com.ciferz.demo.reposetries.Customer.Entity.CustomerEntity;
import com.ciferz.demo.reposetries.supplier.Entity.SupplierEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    private final ModelMapper modelMapper=new ModelMapper();


    public List<CustomerEntity> getAll() {
        List<CustomerEntity> list=customerRepo.findAll();
        return list;
    }

    public CustomerEntity getById(int id) {
        CustomerEntity customerEntity=customerRepo.findById(id).get();
        return customerEntity;
    }

    public ResponseEntity addDatas(List<CustomerModel> customerModels) {
        List<CustomerEntity> customerEntities=modelMapper.map(customerModels,new TypeToken<List<CustomerEntity>>(){}.getType());
        customerRepo.saveAllAndFlush(customerEntities);
        return new ResponseEntity("DATA HAS BEEN ADDED SUCCESSFULLY", HttpStatus.ACCEPTED);
    }

    public ResponseEntity aciveStatus(int id) {
        CustomerEntity customerEntity=customerRepo.findById(id).get();
        customerRepo.update(id,customerEntity.getIsactive() == 1 ? 0 : 1);
        return new ResponseEntity("UPDATED",HttpStatus.ACCEPTED);
    }

}
