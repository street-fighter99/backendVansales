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
    @Query(value = "update ItemEntity De set De.stock=?2 where De.id=?1")
    void updateStocks(int id, int stock);

    @Modifying
    @Query(value = "UPDATE `vansale`.`item` SET `name` =?1, `suppliers` =?4, `stock` =?3, `tax` =?5, `is_active` =?2, `arabic_name`=?7 WHERE (`id` =?6)",nativeQuery = true)
    void updateAll(String name, int isactive, int stock, String suppliers, double vat, int id, String arabicname);

    List<ItemEntity> getByUserId(int id);

    @Query(value = "select * from `vansale`.`item` where id =?1",nativeQuery = true)
    ItemEntity getByIds(int id);
}
