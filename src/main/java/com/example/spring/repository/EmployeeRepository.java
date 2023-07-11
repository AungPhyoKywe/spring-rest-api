package com.example.spring.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.model.Employee;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>, JpaRepository<Employee, Long> {

	List<Employee> findByName(String name);

	List<Employee> findByDepartmentName(String name);

	List<Employee> findByNameAndLocation(String name, String location);

	List<Employee> findByNameContaining(String keyword, Sort sort);

	@Query("FROM Employee WHERE name=:name OR location = :location")
	List<Employee> getEmployeeByNameAndLocations(@Param("name") String name, String location);

	@Transactional
	@Modifying
	@Query("DELETE FROM Employee WHERE name=:name")
	Integer deleteEmployeeByName(String name);

}
