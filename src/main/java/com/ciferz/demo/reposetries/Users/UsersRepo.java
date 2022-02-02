package com.ciferz.demo.reposetries.Users;

import com.ciferz.demo.model.UsersModel;
import com.ciferz.demo.reposetries.Users.Entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UsersRepo extends JpaRepository<UsersEntity,Integer> {

    @Modifying
    @Query(value = "UPDATE `vansale`.`users` SET `name` =?1, `company_name` =?2, `company_name_in_arabic`=?3, `address` =?4,`address_in_arabic` =?5, `tax_no` =?6 WHERE (`phone` =?7)",nativeQuery = true)
    void updates(String name, String companyName, String companyNameInArabic, String address, String addressInArabic, String vatNo, String phone);

    UsersEntity getByPhone(String phone);
}
