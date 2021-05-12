package com.nagarro.assignment5.listener;

import java.time.LocalDate;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.nagarro.assignment5.dto.Employee;
import com.nagarro.assignment5.dto.HrManager;
import com.nagarro.assignment5.repository.EmployeeRepository;
import com.nagarro.assignment5.repository.HrManagerRepository;

@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

    private final HrManagerRepository hrManagerRepository;
    private final EmployeeRepository employeeRepository;

    public ApplicationReadyEventListener(HrManagerRepository hrManagerRepository,
	    EmployeeRepository employeeRepository) {
	this.hrManagerRepository = hrManagerRepository;
	this.employeeRepository = employeeRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

//	addDummyData(hrManagerRepository);
	System.out.println("REST Api has successfully started at http://localhost:9090/");

    }

    // this method adds the initial dummy data to the database
    private static void addDummyData(HrManagerRepository hrManagerRepository) {

	Employee employee1 = new Employee();
	employee1.setCode(1);
	employee1.setName("Employee1");
	employee1.setEmail("emp1@gmail.com");
	employee1.setLocation("Bombay");
	employee1.setDateOfBirth(LocalDate.parse("1990-01-01"));

	Employee employee2 = new Employee();
	employee2.setCode(2);
	employee2.setName("Employee2");
	employee2.setEmail("emp2@gmail.com");
	employee2.setLocation("Gurugram");
	employee2.setDateOfBirth(LocalDate.parse("1996-02-02"));

	Employee employee3 = new Employee();
	employee3.setName("Employee3");
	employee3.setCode(3);
	employee3.setEmail("emp3@gmail.com");
	employee3.setLocation("Jaipur");
	employee3.setDateOfBirth(LocalDate.parse("1998-03-03"));

	HrManager manager1 = new HrManager();
	manager1.setName("manager1");

	HrManager manager2 = new HrManager();
	manager2.setName("manager2");

	HrManager manager3 = new HrManager();
	manager3.setName("manager3");

	manager1.getEmployees().add(employee1);
	manager2.getEmployees().add(employee2);
	manager3.getEmployees().add(employee3);

	hrManagerRepository.save(manager1);
	hrManagerRepository.save(manager2);
	hrManagerRepository.save(manager3);
    }

}
