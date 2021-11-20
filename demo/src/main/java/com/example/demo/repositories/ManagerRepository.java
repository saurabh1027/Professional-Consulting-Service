package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long>{
	public Manager findByEmail(String email);
}
