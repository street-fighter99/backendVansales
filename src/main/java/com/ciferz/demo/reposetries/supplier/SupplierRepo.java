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

    @Modifying
    @Query(value = "update `vansale`.`supplier` set supp_id = ?1, name = ?2, address = ?3, vat_no = ?4, cbalance = ?5, is_active = ?6 where (`user_id` = ?7) and (`supp_id` = ?1)",nativeQuery = true)
    void updateAllCol(int suppId, String name, String address, String vatNo, double cbalance, int isactive, int userId);

    @Modifying
    @Query(value = "delete from `vansale`.`supplier` where (`supp_id` = ?1) and (`user_id` = ?2) limit 1",nativeQuery = true)
    void deleteBYSuppID(int suppID, int userID);

    @Query(value = "select * from `vansale`.`supplier` where (`supp_id` = ?1) and (`user_id` = ?2)",nativeQuery = true)
    SupplierEntity getSuppierByID(int suppID, int userID);

//    @Query(value = "SELECT * FROM vansale.supplier where supp_id = ?1", nativeQuery = true)
//    SupplierEntity findBySuppId(int id);
}
