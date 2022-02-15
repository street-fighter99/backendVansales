package com.ciferz.demo.reposetries.Vat;

import com.ciferz.demo.reposetries.Vat.Entity.VatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VatRepo extends JpaRepository<VatEntity,Integer> {


    VatEntity findByVat(double vat);
}
