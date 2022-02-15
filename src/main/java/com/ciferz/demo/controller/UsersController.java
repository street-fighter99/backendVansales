package com.ciferz.demo.controller;


import com.ciferz.demo.model.UsersModel;
import com.ciferz.demo.reposetries.Users.Entity.UsersEntity;
import com.ciferz.demo.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UsersController {

    @Autowired
    UsersService usersService;

    @GetMapping("/get/all")
    List<UsersEntity> getAllUser(){
        return usersService.getAll();
    }

    @GetMapping("/get/all/byComnyId/{id}")
    List<UsersEntity> getAllUserByCId(@PathVariable int id ){
        return usersService.getAllByCompanyId(id);
    }

    @GetMapping("/get/byPhone/{phone}")
    UsersEntity getByPhone(@PathVariable String phone){
        return usersService.getuserPhone(phone);
    }


    @PostMapping("/update")
    ResponseEntity updateTheUserByPhoneNumber(@RequestBody UsersModel usersModel){
        return usersService.updateByPhoneNo(usersModel);
    }

    @GetMapping("/is/exist/{phone}")
    ResponseEntity isExist(@PathVariable String phone){
        return usersService.isExist(phone);
    }

    @PostMapping("/add/user")
    ResponseEntity saveUser(@RequestBody UsersModel usersModel)
    {
        return usersService.addUser(usersModel);
    }

    @PostMapping("/update/userId/{id}")
    ResponseEntity UpdateStatus(@PathVariable int id)
    {
        return usersService.updateStatus(id);
    }


}
