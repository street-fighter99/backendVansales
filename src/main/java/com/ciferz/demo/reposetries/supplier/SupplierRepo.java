package com.ciferz.demo.reposetries.supplier;

import com.ciferz.demo.reposetries.supplier.Entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface SupplierRepo extends JpaRepository<SupplierEntity,Integer> {

    @Modifying
    @Query(value = "update SupplierEntity De set De.isactive=?2 where De.id=?1 ")
    void update(int id, int isactive);

    @Query(value = "SELECT * FROM vansale.supplier where user_id=?1 and is_active = 1", nativeQuery = true)
    List<SupplierEntity> getByactiveSuppById(int i);

    @Modifying
    @Query(value = "update SupplierEntity De set De.cbalance=?2 where De.id=?1")
    void updateBal(int id, double cbalance);

    List<SupplierEntity> getByUserId(int id);

    @Query(value = "SELECT * FROM vansale.supplier where supp_id = ?1", nativeQuery = true)
    SupplierEntity findBySuppId(int id);
}
