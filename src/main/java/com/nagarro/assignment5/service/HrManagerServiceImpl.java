package com.nagarro.assignment5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.nagarro.assignment5.dto.Employee;
import com.nagarro.assignment5.dto.HrManager;
import com.nagarro.assignment5.repository.EmployeeRepository;
import com.nagarro.assignment5.repository.HrManagerRepository;

@Service
public class HrManagerServiceImpl implements HrManagerService {

    private final HrManagerRepository hrManagerRepository;
    private final EmployeeRepository employeeRepository;

    public HrManagerServiceImpl(HrManagerRepository hrManagerRepository, EmployeeRepository employeeRepository) {
	this.hrManagerRepository = hrManagerRepository;
	this.employeeRepository = employeeRepository;
    }

    // this method finds the list of employees for the particular HR Manager
    @Override
    public List<Employee> getAllEmployeesByHRManagerName(String name) {
	HrManager hrManager = hrManagerRepository.getHrManagerByName(name);
	if (Objects.isNull(hrManager)) {
	    return new ArrayList<>();
	}
	return hrManager.getEmployees();
    }

    @Override
    public HrManager getHrManagerByName(String name) {
	return hrManagerRepository.getHrManagerByName(name);
    }

    @Override
    public Employee getEmloyeeByNameAndCode(String hrName, long employeeCode) {
	return hrManagerRepository.getEmloyeeByNameAndCode(hrName, employeeCode);
    }

    @Override
    public Employee updatEmployeeByNameAndCode(String hrName, long employeeCode, Employee newEmployee) {
	Employee oldEmployee = hrManagerRepository.getEmloyeeByNameAndCode(hrName, employeeCode);
	if (Objects.isNull(oldEmployee)) {
	    return null;
	}
	// inbuild method for copy all the fields to old object
	BeanUtils.copyProperties(newEmployee, oldEmployee, "code");
	Employee savedEmployee = employeeRepository.save(oldEmployee);
	return savedEmployee;
    }

    @Override
    public List<Employee> saveEmployeesForHrManager(String hrName, List<Employee> newEmployees) {
	HrManager hrManager = hrManagerRepository.getHrManagerByName(hrName);
	if (Objects.isNull(hrManager)) {
	    return null;
	}
	// update the common employees of new and old list
	Map<Long, Employee> map = Stream.concat(hrManager.getEmployees().stream(), newEmployees.stream()).collect(
		Collectors.toMap(Employee::getCode, Employee::fetchThisObject, (oldValue, newValue) -> newValue));

	List<Employee> uniqueEmployees = map.values().stream().collect(Collectors.toList());
	hrManager.setEmployees(uniqueEmployees);
	hrManagerRepository.save(hrManager);
	return newEmployees;
    }

}
