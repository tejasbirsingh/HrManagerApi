package com.nagarro.assignment5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.assignment5.dto.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
