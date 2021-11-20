package com.example.demo.services;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.models.Certificate;
import com.example.demo.models.Employee;
import com.example.demo.repositories.CertificateRepository;
import com.example.demo.repositories.EmployeeRepo;
@Service
public class CertificateServiceImpl implements CertificateService{
	@Autowired
	CertificateRepository certificateRepository;
	@Autowired
	EmployeeRepo employeeRepo;

	@Override
	public ResponseEntity<?> createCertificate(Certificate certificate, long id) {
		Employee employee=employeeRepo.getById(id);
		Certificate c=certificateRepository.findByNameAndEmployee(certificate.getName(),employee);
		//if already exist then enter in loop
		if(c!=null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("already exist");
		}
		certificate.setEmployee(employee);
		certificateRepository.save(certificate);
		return ResponseEntity.ok("Certificate created");
		
	}

	@Override
	public ResponseEntity<?> updateCertificate(Certificate newCertificate) {
		//Check whether cert. present or not. if present then update else exceptino
		try {
			Certificate oldCertificate=certificateRepository.findById(newCertificate.getId()).get();
			oldCertificate.setDescription(newCertificate.getDescription());
			certificateRepository.save(oldCertificate);
			
			return ResponseEntity.ok("Certificate updated successfully");
			
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not found");
		}
	}

	@Override
	@Transactional
	public ResponseEntity<?> deleteCertificate(long cid,long eid) {
		try {
			Certificate certificate = certificateRepository.findById(cid).get();
			certificateRepository.deleteByNameAndEmployee(certificate.getName(), certificate.getEmployee());
			return ResponseEntity.ok("Certificate deleted  successfully");
		}  catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not found");
		}
	
	}

	@Override
	public List<Certificate> getCertificates() {
		return certificateRepository.findAll();
	}

	@Override
	public ResponseEntity<List<Certificate>> getCertificatesByEmployee(long id ) {
		try {
			Employee employee=employeeRepo.findById(id).get();
			return ResponseEntity.ok(certificateRepository.findAllByEmployee(employee));
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	
	}
	
	
	

}
