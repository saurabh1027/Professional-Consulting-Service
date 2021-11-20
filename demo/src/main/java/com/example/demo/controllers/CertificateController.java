package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Certificate;
import com.example.demo.services.CertificateService;

@RestController
public class CertificateController {
	@Autowired
	CertificateService certificateService;
	
	@PostMapping("employees/{id}/certificates")
	public ResponseEntity<?> createCertificate(@RequestBody Certificate certificate,@PathVariable("id") long id) {
		
		return certificateService.createCertificate(certificate,id);
		
	}
	@PatchMapping("employees/{eid}/certificates/{cid}")
	public ResponseEntity<?> updateCertificate(@RequestBody Certificate newCertificate,@PathVariable("cid") long cid){
		newCertificate.setId(cid);
		return certificateService.updateCertificate(newCertificate);
	
	}
	@DeleteMapping("employees/{eid}/certificates/{cid}")
	public ResponseEntity<?> deleteCertificate(
			@PathVariable("cid") long cid,
			@PathVariable("eid") long eid){
		return certificateService.deleteCertificate(cid,eid);
	}
	
	@GetMapping("employees/{id}/certificates")
	public ResponseEntity<?> getCertificatesOfEmployee(@PathVariable("id") long id){
		return certificateService.getCertificatesByEmployee(id);
	}
	

}
