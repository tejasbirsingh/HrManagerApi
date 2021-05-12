package com.nagarro.assignment5.service;

import java.util.List;

import com.nagarro.assignment5.dto.Employee;
import com.nagarro.assignment5.dto.HrManager;

public interface HrManagerService {
    
    List<Employee> getAllEmployeesByHRManagerName(String name);

    HrManager getHrManagerByName(String name);

    Employee getEmloyeeByNameAndCode(String hrName, long code);

    Employee updatEmployeeByNameAndCode(String hrName, long employeeCode, Employee newEmployee);

    List<Employee> saveEmployeesForHrManager(String hrName, List<Employee> employees);

}