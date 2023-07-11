package com.example.spring.service;

import java.util.List;
import java.util.Optional;

import com.example.spring.model.Employee;

public interface EmployeeService {

	List<Employee> getEmployees(int pageNumber, int pageSize);

	Employee saveEmployee(Employee employee);

	Employee getSingleEmployee(Long id);

	void deleteEmployee(Long id);

	Employee UpdateEmployee(Employee employee);

	List<Employee> getEmployeesByName(String name);

	List<Employee> getEmployeeByNameAndLocation(String name, String location);

	List<Employee> getEmployeeByKeyword(String keyword);

	List<Employee> getEmployeeByNameAndLocations(String name, String location);

	Integer deleteEmployeeByName(String name);

}
