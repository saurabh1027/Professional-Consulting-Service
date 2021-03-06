package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>{
	public Skill findByTitle(String title); 
	
}
