package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Certificate;

@Repository
public interface CertificateRepo extends JpaRepository<Certificate, Long> {

}
