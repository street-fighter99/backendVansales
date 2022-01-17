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
    @Query(value = "update UsersEntity De set De.name=?1, De.companyName=?2, De.companyNameInArabic=?3, De.address=?4, De.addressInArabic=?5, De.vatNo=?6 where De.phone=?7")
    void updates(String name, String companyName, String companyNameInArabic, String address, String addressInArabic, String vatNo, String phone);

    UsersEntity getByPhone(String phone);
}
