package com.nagarro.assignment5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nagarro.assignment5.dto.Employee;
import com.nagarro.assignment5.dto.HrManager;

@Repository
public interface HrManagerRepository extends JpaRepository<HrManager, Long> {

	@Query("SELECT hm FROM HrManager AS hm WHERE hm.name = :name")
	HrManager getHrManagerByName(@Param("name") String name);

	@Query("SELECT e FROM HrManager AS hm,Employee AS e WHERE hm.name = :name AND e.code = :code")
	Employee getEmloyeeByNameAndCode(@Param("name") String hrName, @Param("code") long employeeCode);

}
