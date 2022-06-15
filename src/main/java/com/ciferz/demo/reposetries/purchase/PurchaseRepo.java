package com.ciferz.demo.reposetries.purchase;

import com.ciferz.demo.reposetries.purchase.Entity.PurchaseEntity;
import com.ciferz.demo.reposetries.sales.Entity.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface PurchaseRepo extends JpaRepository<PurchaseEntity,Integer> {


    List<PurchaseEntity> findAllByOrderByIdDesc();

    @Query(value = "SELECT * FROM vansale.purchase where user_id = ?1 ORDER BY id DESC" ,nativeQuery = true)
    List<PurchaseEntity> getByUserIdDesc(int id);

    @Query(value = "UPDATE `vansale`.`purchase` SET purchase_id = ?1,supplier_id = ?2,item_list=?3,total_amount = ?4,discount = ?5,aftdiscount=?6,net_amount = ?7,paid_amount = ?8,balance = ?9,total_balance = ?10,total_balance = ?11,p_date = ?12  WHERE (`user_id` = ?13 ) and (`purchase_id` = ?1)",nativeQuery = true)
    PurchaseEntity updateAll(int id, int supplierId, String itemList, double totalAmount, double discount, double aftDiscount, double netAmount, double paidAmount, double balance, double totalBalance, double vat, Date tdate, int userId);

    @Modifying
    @Query(value = "delete from `vansale`.`purchase` where (`user_id` =?1) and (`purchase_id` = ?2) limit 1",nativeQuery = true)
    PurchaseEntity delBYPURID(int userID, int purchaseID);

    @Query(value = "select * from `vansale`.`purchase` where month(p_date) = ?1 and year(p_date)=?2",nativeQuery = true)
    List<PurchaseEntity> getbydate(int stmonth, int styear);

    @Query( value = "select * from `vansale`.`purchase` where p_date between ?1 and ?2 and (user_id = ?3)",nativeQuery = true)
    List<PurchaseEntity> getpurchaseBWDates(String stdate, String eddate, int userId);
}
