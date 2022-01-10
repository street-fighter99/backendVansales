package com.ciferz.demo.reposetries.Customer;

import com.ciferz.demo.model.CustomerModel;
import com.ciferz.demo.reposetries.Customer.Entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional
public interface CustomerRepo extends JpaRepository<CustomerEntity,Integer> {

    @Modifying
    @Query(value = "update CustomerEntity De set De.isactive=?2 where De.id=?1")
    void update(int id, int isactive);

}
