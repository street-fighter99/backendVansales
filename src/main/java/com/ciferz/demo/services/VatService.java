package com.ciferz.demo.services;

import com.ciferz.demo.model.VatModel;
import com.ciferz.demo.reposetries.Vat.Entity.VatEntity;
import com.ciferz.demo.reposetries.Vat.VatRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VatService {
    @Autowired
    VatRepo vatRepo;

    ModelMapper modelMapper = new ModelMapper();

    public List<VatEntity> getAll() {
        List<VatEntity> list = vatRepo.findAll();
        return list;
    }

    public ResponseEntity addData(VatModel vatModel) {
        VatEntity vatEntity = modelMapper.map(vatModel,VatEntity.class);
        vatRepo.saveAndFlush(vatEntity);

        return new ResponseEntity<>("added", HttpStatus.ACCEPTED);
    }
}
