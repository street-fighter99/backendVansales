package com.ciferz.demo.reposetries.Companies;

import com.ciferz.demo.reposetries.Companies.Entity.CompaniesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CompaniesRepo extends JpaRepository<CompaniesEntity,Integer> {

    @Modifying
    @Query(value = "update CompaniesEntity De set De.isActive=?2 where De.id=?1")
    void update(int id, int i);
}
