package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Employee;
import com.example.demo.services.EmployeeService;

@CrossOrigin("http://localhost:4200")
@RestController
public class HomeController {

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("authenticate")
	public ResponseEntity<?> authenticateEmployee(
			@RequestParam(name="email") String email,
			@RequestParam(name="password") String password){
		Employee employee = employeeService.getEmployeeByEmail(email);
		if(employee==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No employee found");
		}
		if(password.trim().equals(employee.getPassword().trim())) {
			return ResponseEntity.ok(employee);
		}else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password did not matched!");
		}
	}
	
}
