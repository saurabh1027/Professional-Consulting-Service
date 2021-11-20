package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Certificate;
import com.example.demo.models.Employee;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long>{
	public Certificate findByNameAndEmployee(String name,Employee employee);

	public void deleteByNameAndEmployee(String name,Employee employee);
	
	public List<Certificate> findAllByEmployee(Employee employee);
	
}
