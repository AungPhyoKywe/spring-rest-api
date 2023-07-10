package com.example.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.model.Employee;
import com.example.spring.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService eService;

	@Value("${app.name:Employee Tracker}")
	private String appName;

	@Value("${app.version: Version}")
	private String appVersion;

	@GetMapping("/version")
	public String getAppDetails() {
		return appName + " -" + appVersion;
	}

	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		return eService.getEmployees();
	}

	@GetMapping("/show/employee/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id) {
		return eService.getSingleEmployee(id);
	}

	@PostMapping("/create/employee")
	public Employee saveEmployee(@RequestBody Employee employee) {
		return eService.saveEmployee(employee);
	}

	@PutMapping("/update/employee/{id}")
	public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
		System.out.println("Update employee" + id);
		return employee;
	}

	@DeleteMapping("/delete/employee")
	public void deleteEmployee(@RequestParam("id") Long id) {
		 eService.deleteEmployee(id);
	}

}
