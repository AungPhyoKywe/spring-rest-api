package com.example.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.example.spring.model.Department;
import com.example.spring.model.Employee;
import com.example.spring.repository.DepartmentRepository;
import com.example.spring.repository.EmployeeRepository;
import com.example.spring.request.EmployeeRequest;
import com.example.spring.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService eService;

	@Autowired
	private EmployeeRepository eRepo;

	@Autowired
	private DepartmentRepository dRepo;

	@Value("${app.name:Employee Tracker}")
	private String appName;

	@Value("${app.version: Version}")
	private String appVersion;

	@GetMapping("/version")
	public String getAppDetails() {
		return appName + " -" + appVersion;
	}

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getEmployees(@RequestParam int pageNumber, @RequestParam int pageSize) {
		return new ResponseEntity<List<Employee>>(eService.getEmployees(pageNumber, pageSize), HttpStatus.OK);
	}

	@GetMapping("/show/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
		return new ResponseEntity<Employee>(eService.getSingleEmployee(id), HttpStatus.OK);
	}

	@PostMapping("/create/employee")
	public ResponseEntity<String> saveEmployee(@Valid @RequestBody EmployeeRequest eRequest) {

		Employee employee = new Employee(eRequest);
		employee = eRepo.save(employee);

		for (String s : eRequest.getDepartment()) {
			Department d = new Department();
			d.setName(s);
			d.setEmployee(employee);

			dRepo.save(d);
		}

		return new ResponseEntity<String>("Record Save Successfully", HttpStatus.CREATED);

	}

	@PutMapping("/update/employee/{id}")
	public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
		employee.setId(id);
		return eService.UpdateEmployee(employee);
	}

	@DeleteMapping("/delete/employee")
	public ResponseEntity<HttpStatus> deleteEmployee(@RequestParam("id") Long id) {
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/employees/filterByName")
	public ResponseEntity<List<Employee>> getEmployeesByName(@RequestParam String name) {
		return new ResponseEntity<List<Employee>>(eService.getEmployeesByName(name), HttpStatus.OK);
	}

	@GetMapping("/employees/filterByNameAndLocation")
	public ResponseEntity<List<Employee>> getEmployeesByNameAndLocation(@RequestParam String name,
			@RequestParam String location) {
		return new ResponseEntity<List<Employee>>(eService.getEmployeeByNameAndLocation(name, location), HttpStatus.OK);
	}

	@GetMapping("/employees/filter")
	public ResponseEntity<List<Employee>> getEmployeeByKeyword(@RequestParam String keyword) {
		return new ResponseEntity<List<Employee>>(eService.getEmployeeByKeyword(keyword), HttpStatus.OK);
	}

	@GetMapping("/employees/{name}/{location}")
	public ResponseEntity<List<Employee>> getEmployeesNameAndLocation(@PathVariable String name,
			@PathVariable String location) {
		return new ResponseEntity<List<Employee>>(eService.getEmployeeByNameAndLocation(name, location), HttpStatus.OK);
	}

	@DeleteMapping("/employees/delete/{name}")
	public ResponseEntity<String> deleteEmployeeByName(@PathVariable String name) {
		return new ResponseEntity<String>(eService.deleteEmployeeByName(name) + "No of records deleted", HttpStatus.OK);
	}

	@GetMapping("/employees/department/filter/{name}")
	public ResponseEntity<List<Employee>> getEmployeeByDepartment(@PathVariable String name) {
		return new ResponseEntity<List<Employee>>(eRepo.findByDepartmentName(name), HttpStatus.OK);
	}

}
