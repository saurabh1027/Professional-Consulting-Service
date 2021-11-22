package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	public Employee findByEmail(String email);
	
	public List<Employee> findAllByRole(String role);
	
}
