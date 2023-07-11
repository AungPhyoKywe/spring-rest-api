package com.example.spring.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentResponse {

	private Long id;

	private String departmentName;

	private String employeeName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	
	

}
