package com.ciferz.demo.reposetries.Customer;

import com.ciferz.demo.model.CustomerModel;
import com.ciferz.demo.reposetries.Customer.Entity.CustomerEntity;
import com.ciferz.demo.reposetries.sales.Entity.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CustomerRepo extends JpaRepository<CustomerEntity,Integer> {

    @Modifying
    @Query(value = "update CustomerEntity De set De.isactive=?2 where De.id=?1")
    void update(int id, int isactive);

    @Modifying
    @Query(value = "update CustomerEntity De set De.cbalance=?2 where De.id=?1")
    void updateBal(int id, double cbalance);

    @Query(value = "SELECT * FROM vansale.customer where user_id=?1 and is_active =1", nativeQuery = true)
    List<CustomerEntity> getByactiveuserByuserID(int i);

    @Query(value = "SELECT * FROM vansale.customer where id=?1 and user_id = ?2", nativeQuery = true)
    List<CustomerEntity> getallById(int id,int userID);

    @Modifying
    @Query(value = "UPDATE `vansale`.`customer` SET `name` =?1, `address` =?5, `vat_no` = ?2, `cbalance` = ?3, `is_active` = ?4 WHERE (`id` = ?6)",nativeQuery = true)
    void updateAll(String name, String vatNo, double cbalance, int isactive, String address, int id);

    List<CustomerEntity> getByUserId(int id);

    @Query(value = "delete from `vansale`.`customer` where customer_id = ?1 and user_id = ?2 limit 1", nativeQuery = true)
    void deleteBYcustomerID(int cudtomerId, int userId);
}
