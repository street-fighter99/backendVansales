package com.ciferz.demo.reposetries.Companies;

import com.ciferz.demo.reposetries.Companies.Entity.CompaniesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompaniesRepo extends JpaRepository<CompaniesEntity,Integer> {
}
