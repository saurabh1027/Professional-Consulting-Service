package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Employee;
import com.example.demo.services.EmployeeService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("employees")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("")
	public List<Employee> getEmployees(){
		return employeeService.getEmployees();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
		return employeeService.getEmployeeById(id);
	}
	
//	Register
	@PostMapping("")
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
		return employeeService.createEmployee(employee);
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<?> updateEmployee(
			@RequestBody Employee employee,
			@PathVariable("id") long id) {
		employee.setId(id);
		return employeeService.updateEmployee(employee);
	}
	
	@RequestMapping(path="{id}",method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") long id) {
		return employeeService.deleteEmployee(id);
	}
	
	
	@PutMapping("{id}/skills")
	public ResponseEntity<?> selectSkills(@RequestBody List<Long> skillIds,@PathVariable("id") long eid){
		return employeeService.selectSkills(skillIds,eid);
	}
	
	@PutMapping("{id}/skills/{sId}")
	public ResponseEntity<?> selectSkill(@PathVariable("id") long eid,@PathVariable("sId") long sId){
		return employeeService.selectSkill(eid,sId);
	}
	@DeleteMapping("{id}/skills/{sId}")
	public ResponseEntity<?> deselectSkill(@PathVariable("id") long eid,@PathVariable("sId") long sId){
		return employeeService.deselectSkill(eid,sId);
	}
	
	
	@DeleteMapping("{id}/skills")
	public ResponseEntity<?> deSelectSkills(@RequestBody List<Long> skillIds,@PathVariable("id") long eid){
		return employeeService.deSelectSkills(skillIds,eid);
	}
	
}
