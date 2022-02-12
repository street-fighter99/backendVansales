package com.ciferz.demo.controller;

import com.ciferz.demo.model.CompaniesModel;
import com.ciferz.demo.services.CompaniesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies")
public class CompaniesController {


    @Autowired
    CompaniesService companiesService;

    @GetMapping("/get/all")
    private ResponseEntity getAll(){
        return companiesService.getAllCmpny();
    }

    @PostMapping("/Add")
    public ResponseEntity saveAllData(@RequestBody List<CompaniesModel> companiesModels){
        return  companiesService.addDatas(companiesModels);
    }


}
