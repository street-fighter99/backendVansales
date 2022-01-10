package com.ciferz.demo.reposetries.sales;

import com.ciferz.demo.reposetries.sales.Entity.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SalesRepo extends JpaRepository<SalesEntity,Integer> {

    @Modifying
    @Query(value = "update SalesEntity De set De.isactive=?2 where De.id=?1 ")
    void update(int id, int isactive);
}
