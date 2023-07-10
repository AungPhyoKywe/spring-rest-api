package com.example.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.model.Employee;
import com.example.spring.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository eRepository;

	@Override
	public List<Employee> getEmployees() {

		return eRepository.findAll();
	}

	public Employee getSingleEmployee(Long id) {
		Optional<Employee> employee =  eRepository.findById(id);
		
		if(employee.isPresent()) {
			return employee.get();
		}
		throw new RuntimeException("Employee Not found by ID"+id);
	}

	public Employee saveEmployee(Employee employee) {
		return eRepository.save(employee);
	}
	
	public void deleteEmployee(Long id) {
		 eRepository.deleteById(id);
	}

}
