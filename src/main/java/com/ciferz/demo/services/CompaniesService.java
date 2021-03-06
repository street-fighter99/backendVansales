package com.ciferz.demo.services;

import com.ciferz.demo.model.CompaniesModel;
import com.ciferz.demo.reposetries.Companies.CompaniesRepo;
import com.ciferz.demo.reposetries.Companies.Entity.CompaniesEntity;
import com.ciferz.demo.reposetries.Users.Entity.UsersEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompaniesService {


    @Autowired
    CompaniesRepo companiesRepo;

    private final ModelMapper modelMapper = new ModelMapper();

    public ResponseEntity getAllCmpny() {
        List<CompaniesEntity> allCmpany = companiesRepo.findAll();
        return new ResponseEntity(allCmpany, HttpStatus.ACCEPTED);
    }

    public ResponseEntity addDatas(List<CompaniesModel> companiesModels) {
        List<CompaniesEntity> companiesEntities=modelMapper.map(companiesModels,new TypeToken<List<CompaniesEntity>>(){}.getType());
        companiesRepo.saveAllAndFlush(companiesEntities);
        return new ResponseEntity("DATA HAS BEEN ADDED SUCCESSFULLY", HttpStatus.ACCEPTED);
    }

    public CompaniesEntity getbyCompanyId(int id) {

        return companiesRepo.findById(id).get();
    }

    public ResponseEntity updateStatus(int id) {
        CompaniesEntity companiesEntity=companiesRepo.findById(id).get();
        companiesRepo.update(id,companiesEntity.getIsActive() == 1 ? 0 : 1);
        return new ResponseEntity("UPDATED",HttpStatus.ACCEPTED);
    }
}
