package com.example.demo.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepo;

@Component
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	public ResponseEntity<?> createEmployee(Employee employee) {
		//To check if email is already present
		if(employeeRepo.findByEmail(employee.getEmail().trim()) != null) {
			return ResponseEntity
					.status(HttpStatus.CONFLICT)
					.body("Employee with the same email is already present!");
		}
		if(employee.getRole().trim().equals("Employee")) {
			employeeRepo.save(employee);
			return ResponseEntity.ok().body(null);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@Override
	public ResponseEntity<?> updateEmployee(Employee newEmployee) {
		try {
			Employee oldEmployee = employeeRepo.findById(newEmployee.getId()).get();
			
			//To check if new email is changed, if changed then give bad-request-error
			if(!oldEmployee.getEmail().trim().equals(newEmployee.getEmail().trim())) {
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body("Email cannot be changed");
			}
			
			employeeRepo.save(newEmployee);
			return ResponseEntity.ok().body(null);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@Override
	public ResponseEntity<Employee> getEmployeeById(long id) {
		try {
			Employee employee = employeeRepo.findById(id).get();
			return ResponseEntity.ok().body(employee);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@Override
	public List<Employee> getEmployees() {
		return employeeRepo.findAll();
	}

	@Override
	public ResponseEntity<?> deleteEmployee(long id) {
		try {
			employeeRepo.findById(id).get();
			employeeRepo.deleteById(id);
			return ResponseEntity.ok().body(null);
		}catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

}
