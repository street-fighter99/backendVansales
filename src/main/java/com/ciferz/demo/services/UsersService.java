package com.ciferz.demo.services;

import com.ciferz.demo.model.UsersModel;
import com.ciferz.demo.reposetries.Customer.Entity.CustomerEntity;
import com.ciferz.demo.reposetries.Users.Entity.UsersEntity;
import com.ciferz.demo.reposetries.Users.UsersRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    UsersRepo usersRepo;

   private final ModelMapper modelMapper = new ModelMapper();

    public List<UsersEntity> getAll() {
        List<UsersEntity> list = usersRepo.findAll();
        return list;
    }


    public ResponseEntity updateByPhoneNo(UsersModel usersModel) {
        usersRepo.updates(usersModel.getName(),usersModel.getCompanyName(),usersModel.getCompanyNameInArabic(),usersModel.getAddress(),usersModel.getAddressInArabic(),usersModel.getVatNo(),usersModel.getPhone(),usersModel.getCompanyId(),usersModel.getIsActive());

        return new ResponseEntity("Upadted",HttpStatus.ACCEPTED);
    }

    public ResponseEntity isExist(String phone) {
        UsersEntity usersEntity = usersRepo.getByPhone(phone);
        if (usersEntity == null){

            return new ResponseEntity("this number doesn't exist in the database.", HttpStatus.CONFLICT);
        }else
        {
            return new ResponseEntity(usersEntity, HttpStatus.ACCEPTED);
        }

    }

    public ResponseEntity addUser(UsersModel usersModel) {
        UsersEntity users=modelMapper.map(usersModel,UsersEntity.class);
        usersRepo.saveAndFlush(users);
        return new ResponseEntity("Data has been saved.",HttpStatus.ACCEPTED);

    }

    public UsersEntity getuserPhone(String phone) {
        UsersEntity users = usersRepo.getByPhone(phone);
        return users;
    }

    public List<UsersEntity> getAllByCompanyId(int id) {
        List<UsersEntity> list = usersRepo.getByCompanyId(id);
        return list;
    }

    public ResponseEntity updateStatus(int id) {
        UsersEntity usersEntity=usersRepo.findById(id).get();
        usersRepo.update(id,usersEntity.getIsActive() == 1 ? 0 : 1);
        return new ResponseEntity("UPDATED",HttpStatus.ACCEPTED);
    }
}
