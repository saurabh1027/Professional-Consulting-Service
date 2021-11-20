package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Manager;
import com.example.demo.services.ManagerService;

@RestController
@RequestMapping("manager")
public class ManagerController {
	@Autowired
	ManagerService managerService;
	@PostMapping("")
	public ResponseEntity<?> createManager(@RequestBody Manager manager) {
		return managerService.createManager(manager);
	}

}
