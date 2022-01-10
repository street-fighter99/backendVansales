package com.ciferz.demo.reposetries.purchase;

import com.ciferz.demo.reposetries.purchase.Entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PurchaseRepo extends JpaRepository<PurchaseEntity,Integer> {


}
