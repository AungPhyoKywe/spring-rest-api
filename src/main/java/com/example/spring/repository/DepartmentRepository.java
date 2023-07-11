package com.example.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
