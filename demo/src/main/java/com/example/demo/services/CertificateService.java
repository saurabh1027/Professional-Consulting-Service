package com.example.demo.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.models.Certificate;

public interface CertificateService {
	
	public ResponseEntity<?> createCertificate(Certificate certificate,long id);
	
	public ResponseEntity<?> updateCertificate(Certificate newCertificate);
	
	public ResponseEntity<?> deleteCertificate(long cid,long eid);
	
	public List<Certificate> getCertificates();
	
	public ResponseEntity<List<Certificate>> getCertificatesByEmployee(long id );
	
}
