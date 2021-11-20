package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.models.Manager;
import com.example.demo.repositories.ManagerRepository;

@Component
public class ManagerServiceImpl implements ManagerService {
	@Autowired
	ManagerRepository managerRepository;

	@Override
	public ResponseEntity<?> createManager(Manager manager) {
		//To check if email is already present
				if(managerRepository.findByEmail(manager.getEmail().trim()) != null) {
					return ResponseEntity
							.status(HttpStatus.CONFLICT)
							.body("Manager with the same email is already present!");
				}
				managerRepository.save(manager);
				return ResponseEntity.ok().body("Manager Created!");
	}
	

}
