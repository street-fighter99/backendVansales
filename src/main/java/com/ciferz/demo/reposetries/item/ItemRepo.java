package com.ciferz.demo.reposetries.item;


import com.ciferz.demo.reposetries.item.Entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ItemRepo extends JpaRepository<ItemEntity,Integer> {

    @Modifying
    @Query(value = "update ItemEntity De set De.isactive=?2 where De.id=?1 ")
    void update(int id, int isactive);


    @Modifying
    @Query(value = "update ItemEntity De set De.stock=?2 where De.id=?1")
    void updateStocks(int id, int stock);
}
