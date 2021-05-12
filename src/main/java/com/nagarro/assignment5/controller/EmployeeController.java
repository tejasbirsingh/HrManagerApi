package com.nagarro.assignment5.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.assignment5.dto.Employee;
import com.nagarro.assignment5.exception.CustomException;
import com.nagarro.assignment5.service.HrManagerService;

@RestController
public class EmployeeController {

    private final HrManagerService hrManagerService;

    public EmployeeController(HrManagerService hrManagerService) {
	this.hrManagerService = hrManagerService;
    }

    // returns the data of all the employees for given manager
    @RequestMapping(value = "/hr/{name}/employee", method = RequestMethod.GET)
    public List<Employee> getAllEmployees(@PathVariable String name) throws CustomException {
	List<Employee> employees = hrManagerService.getAllEmployeesByHRManagerName(name);
	if (Objects.isNull(employees) || employees.isEmpty()) {
	    throw new CustomException("There are no employees for HR: " + name);
	}
	return employees;
    }

    // find the employee with given code for particular manager
    @RequestMapping(value = "/hr/{name}/employee/{code}", method = RequestMethod.GET)
    public Employee getOneEmployee(@PathVariable String name, @PathVariable long code) throws CustomException {
	Employee employee = hrManagerService.getEmloyeeByNameAndCode(name, code);
	if (Objects.isNull(employee)) {
	    throw new CustomException("No Employee Found with HR Name : " + name + " , Employee Code : " + code);
	}
	return employee;

    }

    // updates the details of the employee with given code
    @RequestMapping(value = "/hr/{name}/employee/{code}", method = RequestMethod.PUT)
    public Employee updateEmployee(@PathVariable String name, @PathVariable long code, @RequestBody Employee employee)
	    throws CustomException {
	Employee updatedEmployee = hrManagerService.updatEmployeeByNameAndCode(name, code, employee);
	if (Objects.isNull(updatedEmployee)) {
	    throw new CustomException(
		    "Failed To Update. No Employee Found with HR Name : " + name + " , Employee Code : " + code);
	}
	return updatedEmployee;
    }

    // adds the lists of employees retrieved from csv file
    @RequestMapping(value = "/hr/{name}/employees", method = RequestMethod.POST)
    public List<Employee> postEmployees(@PathVariable String name, @RequestBody List<Employee> employees)
	    throws CustomException {
	List<Employee> savedEmployees = hrManagerService.saveEmployeesForHrManager(name, employees);
	if (Objects.isNull(savedEmployees)) {
	    throw new CustomException("Failed To Save! No HR Found with HR Name : " + name);
	}
	return employees;
    }

}
