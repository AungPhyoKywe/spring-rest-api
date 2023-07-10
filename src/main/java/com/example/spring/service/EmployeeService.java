package com.example.spring.service;

import java.util.List;
import java.util.Optional;

import com.example.spring.model.Employee;

public interface EmployeeService {

	List<Employee> getEmployees();

	Employee saveEmployee(Employee employee);

	Employee getSingleEmployee(Long id);

	void deleteEmployee(Long id);
}
