package com.example.demo.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.models.Employee;
import com.example.demo.models.Skill;

public interface EmployeeService {
	public ResponseEntity<?> createEmployee(Employee employee);
	
	public ResponseEntity<?> updateEmployee(Employee employee);
	
	public ResponseEntity<Employee> getEmployeeById(long id);
	
	public List<Employee> getEmployees();
	
	public ResponseEntity<?> deleteEmployee(long id);
	
	public Employee getEmployeeByEmail(String email);

	public ResponseEntity<?> addSkills(List<Long> skillIds, long eid);
	
}
