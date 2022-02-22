package com.ciferz.demo.reposetries.sales;

import com.ciferz.demo.reposetries.sales.Entity.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
@org.springframework.transaction.annotation.Transactional
public interface SalesRepo extends JpaRepository<SalesEntity,Integer> {

    @Modifying
    @Query(value = "update SalesEntity De set De.isactive=?2 where De.id=?1  ")
    void update(int id, int isactive);


    @Query(value = "SELECT * FROM vansale.sales where s_date = ?1 and user_id = ?2" ,nativeQuery = true)
    List<SalesEntity> SDatess(String date,int userid);

    List<SalesEntity> findAllByOrderByIdDesc();

    @Query(value = "SELECT * FROM vansale.sales where user_id = ?1 ORDER BY id DESC" ,nativeQuery = true)
    List<SalesEntity> getByUserIdDesc(int id);

    List<SalesEntity> findAllByUserId(int userId);

    @Query(value = "update vansale.sales set sale_id =?1, customer_id=?2, item_list=?3, total_amount = ?4, discount = ?5, aftdiscount=?6, net_amount = ?7, recieved_amount = ?8,balance = ?9, total_balance = ?10,is_active = ?11,vat = ?12,s_date = ?13 where (`user_id` =?14) and (`sales_id` = ?1)",nativeQuery = true)
    SalesEntity updateAll(int saleId, int customerId, String itemList, double totalAmount, double discount, double aftDiscount, double netAmount, double recievedAmount, double balance, double totalBalance,int isActive, double vat, String tdate, int userId);

    @Query(value = "delete from `vansale`.`sales` where (`user_id` =?1) and (`sale_id` = ?2)")
    void DELBYDSALID(int userID, int salesID);


//    List<SalesEntity> getBySDate(String date);
}
