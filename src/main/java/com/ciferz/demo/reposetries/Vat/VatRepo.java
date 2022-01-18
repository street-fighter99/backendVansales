package com.ciferz.demo.reposetries.Vat;

import com.ciferz.demo.reposetries.Vat.Entity.VatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VatRepo extends JpaRepository<VatEntity,Integer> {
}
