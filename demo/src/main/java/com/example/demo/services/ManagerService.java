package com.example.demo.services;

import org.springframework.http.ResponseEntity;

import com.example.demo.models.Manager;

public interface ManagerService {
	public ResponseEntity<?> createManager(Manager manager);

}
