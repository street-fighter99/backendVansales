package com.ciferz.demo.controller;

import com.ciferz.demo.model.VatModel;
import com.ciferz.demo.reposetries.Vat.Entity.VatEntity;
import com.ciferz.demo.services.VatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vatmaster")
public class VatController {
    @Autowired
    VatService vatService;

    @GetMapping("/get/all")
    public List<VatEntity> getAll(){
        List<VatEntity> list = vatService.getAll();
        return list;
    }

    @PostMapping("/add")
    public ResponseEntity addData(@RequestBody VatModel vatModel){
        return vatService.addData(vatModel);
    }
}
