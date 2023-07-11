package com.example.spring.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.spring.request.EmployeeRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	@NotEmpty(message = "Name should not be null")
	private String name;

	private Long age = 0L;

	private String location;

	@Email(message = "please enter valid email address")
	private String email;

	@JoinColumn(name = "department_id")
	@OneToOne
	private Department department;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private Date createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;
	
	public Employee() {
		
	}

	public Employee(EmployeeRequest req) {
		this.name = req.getName();
	}

	public Date getUpdateAt() {
		return updatedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long Id) {
		this.id = Id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = (long) age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
