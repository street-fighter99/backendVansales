package com.ciferz.demo.reposetries.item;


import com.ciferz.demo.reposetries.item.Entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ItemRepo extends JpaRepository<ItemEntity,Integer> {

    @Modifying
    @Query(value = "update ItemEntity De set De.isactive=?2 where De.id=?1 ")
    void update(int id, int isactive);


    @Modifying
    @Query(value = "update ItemEntity De set De.stock=?2 where De.itemId=?1 and De.userId = ?3")
    void updateStocks(int id, int stock, int user_id);

    @Modifying
    @Query(value = "UPDATE `vansale`.`item` SET `name` =?1, `suppliers` =?4, `stock` =?3, `vat` =?5, `is_active` =?2, `arabic_name`=?7,`item_id` =?6  WHERE (`item_id` =?6) and (`user_id` = ?8)",nativeQuery = true)
    void updateAll(String name, int isactive, int stock, String suppliers, double vat, int id, String arabicname, int userId);

    List<ItemEntity> getByUserId(int id);

    @Query(value = "select * from `vansale`.`item` where id =?1",nativeQuery = true)
    ItemEntity getByIds(int id);

    @Modifying
    @Query( value = "delete from `vansale`.`item` where (`user_id` = ?1) and (`item_id` = ?2) limit 1",nativeQuery = true)
    void deleteByUsrandItemID(int userId, int itemId);

    @Query(value = "select * from `vansale`.`item` where (`user_id` = ?2) and (`item_id` = ?1)",nativeQuery = true)
    ItemEntity getByitemIdAndUserID(int itemId, int userId);
}
