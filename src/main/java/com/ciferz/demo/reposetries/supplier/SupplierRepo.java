package com.ciferz.demo.reposetries.supplier;

import com.ciferz.demo.reposetries.supplier.Entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional
public interface SupplierRepo extends JpaRepository<SupplierEntity,Integer> {

    @Modifying
    @Query(value = "update SupplierEntity De set De.isactive=?2 where De.id=?1 ")
    void update(int id, int isactive);

}
