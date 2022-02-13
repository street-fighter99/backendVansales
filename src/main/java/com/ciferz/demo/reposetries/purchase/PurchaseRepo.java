package com.ciferz.demo.reposetries.purchase;

import com.ciferz.demo.reposetries.purchase.Entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseRepo extends JpaRepository<PurchaseEntity,Integer> {


    List<PurchaseEntity> findAllByOrderByIdDesc();

    @Query(value = "SELECT * FROM vansale.purchase where user_id = ?1 ORDER BY id DESC" ,nativeQuery = true)
    List<PurchaseEntity> getByUserIdDesc(int id);
}
