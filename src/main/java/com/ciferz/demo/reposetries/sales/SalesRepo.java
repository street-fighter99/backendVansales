package com.ciferz.demo.reposetries.sales;

import com.ciferz.demo.reposetries.sales.Entity.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
@Repository
@Transactional
@org.springframework.transaction.annotation.Transactional
public interface SalesRepo extends JpaRepository<SalesEntity,Integer> {

    @Modifying
    @Query(value = "update SalesEntity De set De.isactive=?2 where De.id=?1 ")
    void update(int id, int isactive);


    @Query(value = "SELECT * FROM vansale.sales where s_date = ?1" ,nativeQuery = true)
    List<SalesEntity> SDatess(String date);

    List<SalesEntity> findAllByOrderByIdDesc();

    List<SalesEntity> getByUserId(int id);


//    List<SalesEntity> getBySDate(String date);
}
