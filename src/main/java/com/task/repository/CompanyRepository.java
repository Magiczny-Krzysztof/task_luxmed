package com.task.repository;

import com.task.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query( nativeQuery = true, value = "SELECT * FROM luxmed.companies WHERE ID = :id")
    Company getCompanyByID(@Param("id") Long companyId);
}
