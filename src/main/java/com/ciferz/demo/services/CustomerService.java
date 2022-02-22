package com.ciferz.demo.services;

import com.ciferz.demo.model.CustomerModel;
import com.ciferz.demo.reposetries.Customer.CustomerRepo;
import com.ciferz.demo.reposetries.Customer.Entity.CustomerEntity;
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
        return customerRepo.findById(id).isPresent() ? customerRepo.findById(id).get() : null;
    }

    public ResponseEntity addDatas(List<CustomerModel> customerModels) {
        CustomerEntity customerEntity = getById(customerModels.get(0).getId());
        if (customerEntity !=null){
            return new ResponseEntity("THIS CUSTOMER IS ALREADY IN THE DATABASE", HttpStatus.CONFLICT);
        }
        else {
            List<CustomerEntity> customerEntities = modelMapper.map(customerModels, new TypeToken<List<CustomerEntity>>() {
            }.getType());
            customerRepo.saveAllAndFlush(customerEntities);
            return new ResponseEntity("DATA HAS BEEN ADDED SUCCESSFULLY", HttpStatus.ACCEPTED);
        }
        }

    public ResponseEntity aciveStatus(int id) {
        CustomerEntity customerEntity=customerRepo.findById(id).get();
        customerRepo.update(id,customerEntity.getIsactive() == 1 ? 0 : 1);
        return new ResponseEntity("UPDATED",HttpStatus.ACCEPTED);
    }

    public ResponseEntity updateBal(CustomerModel customerModel) {
        customerRepo.updateBal(customerModel.getId(),customerModel.getCbalance());

       return new ResponseEntity("Balance updated",HttpStatus.ACCEPTED);

    }

    public List<CustomerEntity> getActiveCustomer(int id) {
        List<CustomerEntity> list = customerRepo.getByactiveuserByuserID(id);
        return list;
    }

    public ResponseEntity updateAll(CustomerModel customerModel) {
        customerRepo.updateAll(customerModel.getName(),customerModel.getVatNo(),customerModel.getCbalance(),customerModel.getIsactive(),customerModel.getAddress(),customerModel.getCustomerId(),customerModel.getUserId());
        return new ResponseEntity("Customer details updated.",HttpStatus.ACCEPTED);

    }

    public List<CustomerEntity> getAllByUserID(int id) {
        return customerRepo.getByUserId(id);
    }

    public ResponseEntity deleteBycustomerID(int cudtomerId, int userId) {
        customerRepo.deleteBYcustomerID(cudtomerId,userId);
        return new ResponseEntity("data has been deleted",HttpStatus.ACCEPTED);
    }
}
