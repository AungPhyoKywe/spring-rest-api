package com.example.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.model.Department;
import com.example.spring.repository.DepartmentRepository;
import com.example.spring.response.DepartmentResponse;

@RestController
public class DepartmentController {
	
	@Autowired
	private DepartmentRepository dRepo;
	
	@GetMapping("/departments")
	public List<DepartmentResponse> getDepartments()
	{
		List<Department> depts = dRepo.findAll();
		List<DepartmentResponse> list = new ArrayList<>();
		
		depts.forEach(d ->{
			DepartmentResponse dResponse = new DepartmentResponse();
			dResponse.setDepartmentName(d.getName());
			dResponse.setId(d.getId());
			dResponse.setEmployeeName(d.getEmployee().getName());
			list.add(dResponse);
		});
		
		return list;
	}

}
